package com.example.usneakers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME="Usermanager.db";
    private static final String TABLE_USER="User";
    private static final String COLUMN_USER_ID="user_id";
    private static final String COLUMN_USER_NAME="user_name";
    private static final String COLUMN_USER_EMAIL="user_email";
    private static final String COLUMN_USER_PASS="user_password";

    /*create table*/
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL +" TEXT,"
            + COLUMN_USER_PASS + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    /*get user account info by their email*/
    public User getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_EMAIL + " LIKE '" + email + "'";

        Cursor cursor = db.rawQuery(sql, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
            user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));;
            user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
        }
        return user;
    }

    /*update user name by their email*/
    public User upDateUserName(String name, String email){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "UPDATE " + TABLE_USER + " SET " + COLUMN_USER_NAME + " = ' " + name + " ' " + " WHERE " + COLUMN_USER_EMAIL + " LIKE '" + email + "'";

        Cursor cursor = db.rawQuery(sql, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
            user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));;
            user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
        }
        return user;
    }

    /*add user records in sqlite db*/
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASS, user.getPass());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /*fetch data to check exist data*/
    boolean checkUser(String email){
        String[] column = {COLUMN_USER_ID};

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER, column, selection, selectionArgs, null, null, null);
        int cursor_count = cursor.getCount();
        db.close();
        if(cursor_count > 0 ){
            return true;
        }
        return false;
    }

    /*check for email id and password records*/
    boolean checkUser(String email, String pass){
        String[] column = {COLUMN_USER_ID};

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASS + " = ?";
        String[] selectionArgs = {email, pass};

        Cursor cursor = db.query(TABLE_USER, column, selection, selectionArgs, null, null, null);
        int cursor_count = cursor.getCount();
        db.close();
        if(cursor_count > 0 ){
            return true;
        }
        return false;
    }
}
