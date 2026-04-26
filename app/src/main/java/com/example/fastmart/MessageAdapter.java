package com.example.fastmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MessageAdapter extends ArrayAdapter<Message> {

    private static final int VIEW_TYPE_SENT     = 0;
    private static final int VIEW_TYPE_RECEIVED = 1;

    Context context;
    ArrayList<Message> messages;
    String currentUserId;

    public MessageAdapter(Context context, ArrayList<Message> messages, String currentUserId) {
        super(context, 0, messages);
        this.context       = context;
        this.messages      = messages;
        this.currentUserId = currentUserId;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        return message.getSenderId().equals(currentUserId) ? VIEW_TYPE_SENT : VIEW_TYPE_RECEIVED;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = messages.get(position);
        int viewType = getItemViewType(position);

        if (convertView == null) {
            if (viewType == VIEW_TYPE_SENT) {
                convertView = LayoutInflater.from(context)
                        .inflate(R.layout.item_message_sent, parent, false);
            } else {
                convertView = LayoutInflater.from(context)
                        .inflate(R.layout.item_message_received, parent, false);
            }
        }

        TextView tvMessage = convertView.findViewById(R.id.tvMessage);
        TextView tvTime    = convertView.findViewById(R.id.tvTime);

        tvMessage.setText(message.getMessage());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        tvTime.setText(sdf.format(new Date(message.getTimestamp())));

        return convertView;
    }
}