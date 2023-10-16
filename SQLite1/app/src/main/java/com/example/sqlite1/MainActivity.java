package com.example.sqlite1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sqlite1.Adapter.RecyclerViewAdapter;
import com.example.sqlite1.Data.DBHandler;
import com.example.sqlite1.Model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.contactRecycle);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DBHandler db = new DBHandler(MainActivity.this);
        Contact sc = new Contact();
        sc.setPhoneNumber("909999525");
        sc.setName("Kshitij");
        db.addContact(sc);
        Contact sc2 = new Contact();
        sc2.setPhoneNumber("909999585");
        sc2.setName("Jaiswal");
        db.addContact(sc2);
        db.addContact(sc);
        db.addContact(sc);
        db.addContact(sc2);
        db.addContact(sc);
        db.addContact(sc);
        db.addContact(sc2);
        db.addContact(sc2);
        db.addContact(sc2);
        db.addContact(sc);
        db.addContact(sc);
        db.addContact(sc);
        Log.d("dbKshitij", "The Logs are added");

        //This is for list view
        /*ArrayList<String> list=new ArrayList<>();
        ListView cont=findViewById(R.id.contactlist);
        ArrayAdapter<String> adpt=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        cont.setAdapter(adpt);*/

        contactArrayList=new ArrayList<>();

        //Get all contacts
        List<Contact> allContacts = db.getAllcontacts();
        for (Contact contact : allContacts) {
            Log.d("dbKshitij", "\nId: " + contact.getId() + "\n" +
                    "Name: " + contact.getName() + "\n" +
                    "Phone Number: " + contact.getPhoneNumber() + "\n");
            contactArrayList.add(contact);
            //list.add(contact.getName()+" "+contact.getPhoneNumber());
        }
        //USing recyclerView

        recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);



        //How to change/update and delete
        /*sc2.setName("cHANGED KSHITIJ");
        sc2.setPhoneNumber("1111111111");
        int affectedrows=db.updateContacts(sc2);
        Log.d("dbKshitij","No.of affected rows: "+affectedrows);


        db.deleteContactsByid(1);
        Log.d("dbKshitij","You have "+db.getCount()+" Contacts");*/
    }
}