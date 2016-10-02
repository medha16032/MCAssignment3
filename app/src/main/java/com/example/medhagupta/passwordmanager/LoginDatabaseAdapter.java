package com.example.medhagupta.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by Medha Gupta on 10/1/2016.
 */

public class LoginDatabaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE = "create table if not exists "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "ACCOUNT_NAME text, USERNAME  text,PASSWORD text); ";

    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DatabaseHelper dbHelper;
    public  LoginDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDatabaseAdapter open()
    {
        try {


            db = dbHelper.getWritableDatabase();
            return this;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String accountName,String userName,String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("ACCOUNT_NAME", accountName);
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);
        //newValues.put("URL", url);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public void deleteEntry(String accountName)
    {
        //String id=String.valueOf(ID);
        //  String where="USERNAME=?";
        // int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        //  return numberOFEntriesDeleted;

        String[] args = new String[]{accountName};
        db.delete("LOGIN", "ACCOUNT_NAME=?", args);

    }
    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        //Toast.makeText(LoginDatabaseAdapter.this, "password"", Toast.LENGTH_SHORT).show();
        cursor.close();
        return password;
    }
    public String getUsername(String account_name)
    {
        Cursor cursor=db.query("LOGIN", null, " ACCOUNT_NAME=?", new String[]{account_name}, null, null, null);
        cursor.moveToFirst();
        String userName= cursor.getString(cursor.getColumnIndex("USERNAME"));
        cursor.close();
        return userName;
    }

    public String getPassword(String account_name)
    {
        Cursor cursor=db.query("LOGIN", null, " ACCOUNT_NAME=?", new String[]{account_name}, null, null, null);
        cursor.moveToFirst();
        String userName= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return userName;
    }


    public void  updatePassword(String userName,String password)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);


        // String where="USERNAME = ?";
        String[] args = new String[]{userName};
        db.update("LOGIN", updatedValues, "USERNAME=?", args);



    }




    public void  updateEntry(String account_name,String userName,String password)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        //updatedValues.put("ACCOUNTNAME", accountName);
        updatedValues.put("ACCOUNT_NAME", account_name);
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);


        // String where="USERNAME = ?";
        String[] args = new String[]{account_name};
        db.update("LOGIN",updatedValues,"ACCOUNT_NAME=?",args);

        // db.update("LOGIN",updatedValues, where, new String[]{userName});

    }

        public boolean isAccount(String account_name)
        {
            Cursor cursor=db.query("LOGIN", null, " ACCOUNT_NAME=?", new String[]{account_name}, null, null, null);
            cursor.moveToFirst();

            if(cursor.getCount()>0) // UserName Not Exist
            {
                cursor.close();
                return false;
            }

            cursor.close();
            return true;

        }
    }
