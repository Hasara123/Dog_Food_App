package com.example.dog_food_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app_db";
    private static final int DATABASE_VERSION = 1;

    // User table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_ADDRESS = "address";


    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT, " +
            COLUMN_ADDRESS + " TEXT, " +
            COLUMN_EMAIL + " TEXT UNIQUE);";



    public boolean updateUserProfile(String email, String username, String newEmail, String address, String phoneNumber, String paymentMethods) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_EMAIL, newEmail);
        contentValues.put(COLUMN_ADDRESS, address);

        int result = db.update(TABLE_USERS, contentValues, COLUMN_EMAIL + " = ?", new String[]{email});
        db.close();
        return result > 0; // Return true if update was successful
    }
    // Method to fetch user data by email
    public Cursor getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USERS, null, COLUMN_EMAIL + " = ?", new String[]{email}, null, null, null);
    }




    // Cart table
    public static final String TABLE_CART = "cart";
    public static final String COLUMN_CART_ID = "cart_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_USER_EMAIL = "user_email";

    private static final String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + " (" +
            COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PRODUCT_NAME + " TEXT, " +
            COLUMN_QUANTITY + " INTEGER, " +
            COLUMN_USER_EMAIL + " TEXT, " +
            "FOREIGN KEY(" + COLUMN_USER_EMAIL + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_EMAIL + "));";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    // Method to add a new user to the database
    public boolean addUser(String username, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_EMAIL, email);

        long result = db.insert(TABLE_USERS, null, contentValues);
        db.close();
        return result != -1; // Return true if insertion was successful
    }

    // Method to check if a user exists by email
    public boolean checkUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Method to check user credentials
    public boolean checkUserCredentials(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        boolean valid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return valid;
    }

    // Method to add item to the cart
    public boolean addToCart(String productName, int quantity, String userEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCT_NAME, productName);
        contentValues.put(COLUMN_QUANTITY, quantity);
        contentValues.put(COLUMN_USER_EMAIL, userEmail);

        long result = db.insert(TABLE_CART, null, contentValues);
        db.close();
        return result != -1; // Return true if insertion was successful
    }

    // Method to get cart items for a user
    public Cursor getCartItems(String userEmail) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CART + " WHERE " + COLUMN_USER_EMAIL + " = ?";
        return db.rawQuery(query, new String[]{userEmail});
    }

    // Method to clear the cart for a user
    public boolean clearCart(String userEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_CART, COLUMN_USER_EMAIL + " = ?", new String[]{userEmail});
        db.close();
        return result > 0; // Return true if any rows were deleted
    }
}