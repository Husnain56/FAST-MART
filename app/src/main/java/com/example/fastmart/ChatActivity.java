package com.example.fastmart;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    ListView lvMessages;
    EditText etMessage;
    ImageView btnSend, btnBack;
    TextView tvReceiverName;

    MessageAdapter adapter;
    ArrayList<Message> messages;

    String currentUserId, receiverId, receiverName, chatId;
    DatabaseReference chatRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        receiverId   = getIntent().getStringExtra("receiverId");
        receiverName = getIntent().getStringExtra("receiverName");
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        chatId = currentUserId.compareTo(receiverId) < 0
                ? currentUserId + "_" + receiverId
                : receiverId + "_" + currentUserId;

        chatRef = FirebaseDatabase.getInstance().getReference()
                .child("chats").child(chatId).child("messages");

        init();
        loadMessages();
    }

    private void init() {
        lvMessages    = findViewById(R.id.lvMessages);
        etMessage     = findViewById(R.id.etMessage);
        btnSend       = findViewById(R.id.btnSend);
        btnBack       = findViewById(R.id.btnBack);
        tvReceiverName = findViewById(R.id.tvReceiverName);

        tvReceiverName.setText(receiverName);

        messages = new ArrayList<>();
        adapter  = new MessageAdapter(this, messages, currentUserId);
        lvMessages.setAdapter(adapter);

        btnBack.setOnClickListener(v -> finish());

        btnSend.setOnClickListener(v -> {
            String text = etMessage.getText().toString().trim();
            if (text.isEmpty()) return;
            sendMessage(text);
            etMessage.setText("");
        });
    }

    private void sendMessage(String text) {
        Map<String, Object> message = new HashMap<>();
        message.put("senderId",   currentUserId);
        message.put("receiverId", receiverId);
        message.put("message",    text);
        message.put("timestamp",  ServerValue.TIMESTAMP);
        chatRef.push().setValue(message);
    }

    private void loadMessages() {
        chatRef.orderByChild("timestamp")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messages.clear();
                        for (DataSnapshot child : snapshot.getChildren()) {
                            Message message = child.getValue(Message.class);
                            if (message != null) messages.add(message);
                        }
                        adapter.notifyDataSetChanged();
                        lvMessages.setSelection(adapter.getCount() - 1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
    }
}