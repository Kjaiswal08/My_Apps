package com.example.volleyapidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This just bring one piece of data(object).
        /*RequestQueue newRequest= Volley.newRequestQueue(this);
        JsonObjectRequest objarray=new JsonObjectRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos/1", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("myapp","Response is:"+response.getString("title"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp","Something gone wrong");
            }
        });
        newRequest.add(objarray);*/
        //This just bring all the piece of data(array of objects).
        RequestQueue newRequest= Volley.newRequestQueue(this);
        JsonArrayRequest jsonarray=new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<=response.length();i++)
                    {
                        JSONObject res=response.getJSONObject(i);
                        Log.d("myapp","onResponse: "+res.getString("title"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp","Something gone wrong");
                Toast.makeText(MainActivity.this, "Something went wromg", Toast.LENGTH_SHORT).show();
            }
        });
        newRequest.add(jsonarray);
    }
}