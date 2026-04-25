package com.example.fastmart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FavouriteDB {

    private static final String DB_NAME          = "FavouriteDB";
    private static final int    DB_VERSION       = 1;
    private static final String TABLE_NAME       = "favourites";
    private static final String COL_PRODUCT_ID       = "productId";
    private static final String COL_NAME             = "name";
    private static final String COL_CATEGORY         = "category";
    private static final String COL_ORIGINAL_PRICE   = "originalPrice";
    private static final String COL_DISCOUNTED_PRICE = "discountedPrice";
    private static final String COL_DESCRIPTION      = "description";
    private static final String COL_SELLER_UID       = "sellerUid";
    private static final String COL_CREATED_AT       = "createdAt";

    private final ProductOpenHelper helper;

    public FavouriteDB(Context context) {
        helper = new ProductOpenHelper(context);
    }

    public void addFavourite(ProductItem product) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PRODUCT_ID,       product.getProductId());
        values.put(COL_NAME,             product.getName());
        values.put(COL_CATEGORY,         product.getCategory());
        values.put(COL_ORIGINAL_PRICE,   product.getOriginalPrice());
        values.put(COL_DISCOUNTED_PRICE, product.getDiscountedPrice());
        values.put(COL_DESCRIPTION,      product.getDescription());
        values.put(COL_SELLER_UID,       product.getSellerUid());
        values.put(COL_CREATED_AT,       product.getCreatedAt());
        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void removeFavourite(String productId) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL_PRODUCT_ID + " = ?",
                new String[]{productId});
        db.close();
    }

    public boolean isFavourite(String productId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COL_PRODUCT_ID + " = ?", new String[]{productId});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public ArrayList<ProductItem> getAllFavourites() {
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<ProductItem> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                ProductItem product = new ProductItem(
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_PRODUCT_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_CATEGORY)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COL_ORIGINAL_PRICE)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COL_DISCOUNTED_PRICE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_SELLER_UID)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COL_CREATED_AT))
                );
                list.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    private static class ProductOpenHelper extends SQLiteOpenHelper {

        public ProductOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                    COL_PRODUCT_ID       + " TEXT PRIMARY KEY, " +
                    COL_NAME             + " TEXT, " +
                    COL_CATEGORY         + " TEXT, " +
                    COL_ORIGINAL_PRICE   + " REAL, " +
                    COL_DISCOUNTED_PRICE + " REAL, " +
                    COL_DESCRIPTION      + " TEXT, " +
                    COL_SELLER_UID       + " TEXT, " +
                    COL_CREATED_AT       + " INTEGER)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}