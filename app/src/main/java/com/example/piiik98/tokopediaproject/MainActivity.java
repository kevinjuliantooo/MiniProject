package com.example.piiik98.tokopediaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView sourceListview;

    private ArrayList<String> nameArray = new ArrayList<>();
    private ArrayList<String> descriptionArray = new ArrayList<>();
    private ArrayList<String> categoryArray = new ArrayList<>();
    private ArrayList<String> idArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /************* Widget Used *********** **/
        sourceListview = findViewById(R.id.sourceListview);

        //************************ For Connection and API ************************//
        String url = "https://newsapi.org/v2/sources?apiKey=d04a012c1855439abc3ea73dd884dfce";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //************************ Listview Clicked ************************//
        sourceListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this, ChoosedSourceActivity.class);
                    intent.putExtra("id", idArray.get(i));
                    startActivity(intent);
            }
        });

        //************************ Parsing JSON with GSON ************************//
        JsonObjectRequest objectRequest= new JsonObjectRequest( Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Respone", response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("sources");
                    SourceAdapter sourceAdapter = new SourceAdapter(getApplicationContext(), nameArray, descriptionArray, categoryArray, idArray);

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject sources = jsonArray.getJSONObject(i);

                        String name = sources.getString("name");
                        String description = sources.getString("description");
                        String category = sources.getString("category");
                        String id = sources.getString("id");

                        nameArray.add(name);
                        descriptionArray.add(description);
                        categoryArray.add(category);
                        idArray.add(id);

                    }
                    sourceListview.setAdapter(sourceAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Respone", error.toString());
            }
        }
        );
        requestQueue.add(objectRequest);
    }
}
