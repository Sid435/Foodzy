package com.example.foodzy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHandler extends SQLiteOpenHelper {

    public static final String DBname = "Foodzy_userDB";
    public DBHandler(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q = "create table users (mail text primary key, name text, password text, phone_no text)";
        sqLiteDatabase.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }

    public boolean insert_data(String mail, String name, String password, String phone_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("mail",mail);
        c.put("name",name);
        c.put("password",password);
        c.put("phone_no",phone_no);
        long r = db.insert("users", null, c);

        if (r==-1) return false;
        else
            return true;
    }

    public boolean check_user(String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where mail=?", new String[]{mail});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean check_user_pass(String mail,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where mail=? and password=?", new String[]{mail,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor get_phone(String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select phone_no from users where mail=?",new String[]{mail} );
//        Cursor cursor = db.rawQuery("select phone from users where", null);
        return cursor;
    }

    public Cursor get_name(String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name from users where mail=?",new String[]{mail} );
        return cursor;
    }

    public boolean update_pass(String mail, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("password", password);
        int r = db.update("users", c, "mail=?", new String[]{mail});
        if (r == -1)
            return false;
        else
            return true;
//        Cursor cursor = db.rawQuery("select * from users where username=?", new String[]{username});
        // System.out.println(cursor.getString(0));
//        if (cursor.getCount() > 0) {
//
//
////            while(cursor.moveToNext()){
////                System.out.println(cursor.getString(1));
////            }
//
//        }
//        else {
//            return false;
//        }
    }
}
