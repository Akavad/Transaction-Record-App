package com.example.dell.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 08-02-2018.
 */

public class TransactionOpenHelper extends SQLiteOpenHelper {

    String date;

    public TransactionOpenHelper(Context context) {
        super(context,"TRANSACTION",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table trans(ID integer primary key autoincrement,DATE varchar(10),TYPE varchar(6),AMOUNT int,PARTICULAR varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertTransaction(Transaction transaction){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("DATE",transaction.getDate());
        values.put("TYPE",transaction.getType());
        values.put("AMOUNT",transaction.getAmount());
        values.put("PARTICULAR",transaction.getParticular());

        db.insert("trans",null,values);
        db.close();
    }

    public void deleteTransaction(int id){
        SQLiteDatabase db=this.getWritableDatabase();

        String table="trans";
        String whereClause="ID=?";
        String[] whereArgs={String.valueOf(id)};

        db.delete(table,whereClause,whereArgs);
        db.close();
    }

    public List<Bundle> displayTransaction(String search_date){

        List<Bundle> list=new ArrayList<Bundle>();
        SQLiteDatabase db=this.getReadableDatabase();
        date=new Date().getDate();

        String table="trans";
        String[] columns=null;
        String selection="DATE Like ?";
        if (search_date.equals(date.substring(3,5))){
            search_date="___"+search_date+"%";
            Log.i("month",search_date);
        }

        String[] selectionArgs={search_date};
        String groupBy=null;
        String having=null;
        String orderBy=null;

        Cursor cursor=db.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);

        if (cursor==null)
            return null;

        if (cursor.moveToFirst()){
            do {
                Bundle bundle=new Bundle();
                bundle.putInt("ID",cursor.getInt(0));
                bundle.putString("DATE",cursor.getString(1));
                bundle.putString("TYPE",cursor.getString(2));
                bundle.putInt("AMOUNT",cursor.getInt(3));
                bundle.putString("PARTICULAR",cursor.getString(4));
                list.add(bundle);
            }while (cursor.moveToNext());
        }

        return list;
    }

    public List<Transaction> diaplayTransaction(String selection,String[] selectionArgs){
        List<Transaction> list=new ArrayList<Transaction>();
        SQLiteDatabase db=this.getReadableDatabase();

        String table="trans";
        String[] columns=null;
        String groupBy=null;
        String having=null;
        String orderBy=null;

        Cursor cursor=db.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);

        if (cursor==null)
            return null;

        if (cursor.moveToFirst()){
            do {
                Transaction transaction=new Transaction();
                transaction.setId(cursor.getInt(0));
                transaction.setDate(cursor.getString(1));
                transaction.setType(cursor.getString(2));
                transaction.setAmount(cursor.getInt(3));
                transaction.setParticular(cursor.getString(4));
                list.add(transaction);
            }while (cursor.moveToNext());
        }

        return list;
    }
}
