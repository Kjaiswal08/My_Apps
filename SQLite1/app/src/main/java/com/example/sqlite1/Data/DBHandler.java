package com.example.sqlite1.Data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.sqlite1.Model.Contact;
import com.example.sqlite1.Parameters.Params;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    public DBHandler(Context context) {
        super(context, Params.DB_name,null,Params.DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="Create Table "+Params.Table_name+"("+Params.Key_id+" Integer Primary Key, "+Params.Key_name+" Text, "+ Params.Key_phone+" Text"+")";
        Log.d("Database","Query is running"+create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addContact(Contact cont)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Params.Key_name,cont.getName());
        values.put(Params.Key_phone,cont.getPhoneNumber());
        db.insert(Params.Table_name,null,values);
        Log.d("dbKshitij","Successfully inserted");
        db.close();

    }
    public List<Contact> getAllcontacts()
    {
        List<Contact> contactsList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        //Generate Query to read db.
        String select="SELECT * FROM "+Params.Table_name;
        Cursor cursor=db.rawQuery(select,null);
        if(cursor.moveToFirst())
        {
            do {
                Contact contact=new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactsList.add(contact);
                Log.d("duringinsertion",contact.getName()+" "+contact.getPhoneNumber());
            }while(cursor.moveToNext());
        }
        return contactsList;
    }
    public int updateContacts(Contact contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(Params.Key_name,contact.getName());
        value.put(Params.Key_phone,contact.getPhoneNumber());
        return db.update(Params.Table_name,value,Params.Key_id+"=?",new String[]{String.valueOf(contact.getId())});
    }
    public void deleteContactsByid(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Params.Table_name,Params.Key_id+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteContacts(Contact contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Params.Table_name,Params.Key_id+"=?",new String[]{String.valueOf(contact.getId())});
        db.close();
    }
    public int getCount()
    {
        String query="SELECT * FROM "+Params.Table_name;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();
    }
}
