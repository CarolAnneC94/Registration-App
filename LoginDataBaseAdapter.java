package com.example.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class LoginDataBaseAdapter {

    private static  final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Login.db";

    private static SQLiteDatabase db;

    private DatabaseHelper databaseHelper;

    public LoginDataBaseAdapter(Context context){
        databaseHelper= new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public  LoginDataBaseAdapter open() throws SQLException{
        db = databaseHelper.getWritableDatabase();
        return this;
    }

   public void close(){
        db.close();
   }

   public  SQLiteDatabase getDatabaseInstance(){
        return db;
   }

   public void insertEntry(String un, String email, String pw, String Gender){
       ContentValues newValues = new ContentValues();
       newValues.put("userName", un);
       newValues.put("userPassword", pw);
       newValues.put("userEmail", email);
       newValues.put("userGender", Gender);
       db.insert("User", null, newValues);
   }

   public String getSingleEntry(String un){
        db = databaseHelper.getReadableDatabase();
       Cursor cursor = db.query("User", null, "userName=?", new String[]{un}, null, null, null);
       if(cursor.getCount() < 1){
           cursor.close();
           return "NOT EXIST";
       }
       cursor.moveToFirst();
       String getPassword = cursor.getString(cursor.getColumnIndex("userPassword"));
       cursor.close();
       return getPassword;
   }

    public ArrayList<User> getAllUsers(){
        db = databaseHelper.getReadableDatabase();
        ArrayList<User> users = new ArrayList<User>();
        Cursor cursor = db.rawQuery("Select * From User", null);
        try {
            if(cursor.moveToFirst()){
                do{
                    String UserName = cursor.getString(1);
                    String UserPassword = cursor.getString(3);
                    users.add(new User(UserName, UserPassword));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("LoginDBAdapter: ", "Error while trying to get users from database" + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return users;

    }
}
