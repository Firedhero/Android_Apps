package com.example.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.LinkedList;

public class DatabaseManager extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "DATABASE";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "PASSWORDS";

    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        String command = "create table " + TABLE_NAME + "(" +
                         "Location text, " +
                         "Password text )";

        db.execSQL(command);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public void insert(Account account)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("Location", account.getLocation());
        row.put("Password", account.getPassword());
        db.insert(TABLE_NAME, null, row);

        db.close();
    }

    public void delete(String name)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, "Location = ?", new String[]{name});

        db.close();
    }

    public void update(Account account)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("Location", account.getLocation());
        row.put("Password", account.getPassword());
        db.update(TABLE_NAME, row, "Location = ?", new String[]{account.getLocation()});

        db.close();
    }



    public LinkedList<Account> all()
    {
        SQLiteDatabase db = getWritableDatabase();

        LinkedList<Account> list = new LinkedList<Account>();

        Cursor cursor = db.query(TABLE_NAME, new String[]{"Location", "Password"},
                                 null, null, null, null, null);

        while (cursor.moveToNext())
        {
            String locaion = cursor.getString(0);
            String password = cursor.getString(1);
            Account account = new Account(locaion,password);
            list.addLast(account);
        }

        cursor.close();
        db.close();

        return list;
    }

    public void clear()
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, null, null);

        db.close();
    }
}