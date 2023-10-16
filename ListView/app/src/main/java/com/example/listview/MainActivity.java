package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list=findViewById(R.id.listView);
        ArrayList<String> grocery=new ArrayList<>();
        grocery.add("Rice");grocery.add("Fruits");grocery.add("Bread");grocery.add("Flour");grocery.add("Dal");
        ArrayAdapter<String> arrayadd=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,grocery);
        list.setAdapter(arrayadd);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String st="Item"+position+" "+((TextView) view).getText().toString();
                Toast.makeText(MainActivity.this,st,Toast.LENGTH_SHORT).show();
            }
        });

    }
}