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
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChoosedSourceActivity extends AppCompatActivity {

    private ListView articleListview;
    private String choosedSource, url;

    private ArrayList<String> titleArray = new ArrayList<>();
    private ArrayList<String> descriptionArray = new ArrayList<>();
    private ArrayList<String> urlArray = new ArrayList<>();
    private ArrayList<String> urlToImageArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosed_source);

        //************************ For API and Choosen Source ************************//
        choosedSource = getIntent().getExtras().getString("id");
        url = "https://newsapi.org/v2/top-headlines?sources=" + choosedSource + "&apiKey=d04a012c1855439abc3ea73dd884dfce";

        //************************ Help Network ************************//
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //************************ Widget Used ************************//
        articleListview = findViewById(R.id.articleListView);


        //************************ Parsing JSON with GSON ************************//
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("respone", response.toString());

                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    ArticleAdapter articleAdapter = new ArticleAdapter(getApplicationContext(), titleArray, descriptionArray, urlArray, urlToImageArray);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject article = jsonArray.getJSONObject(i);

                        String title = article.getString("title");
                        String description = article.getString("description");
                        String url = article.getString("url");
                        String urlToImage = article.getString("urlToImage");

                        titleArray.add(title);
                        descriptionArray.add(description);
                        urlArray.add(url);
                        urlToImageArray.add(urlToImage);
                    }
                    System.out.println(titleArray);
                    articleListview.setAdapter(articleAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("respone", error.toString());
            }
        });
        requestQueue.add(objectRequest);

        articleListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ChoosedSourceActivity.this, WebviewActivity.class);
                intent.putExtra("webURL", urlArray.get(i));
                startActivity(intent);
            }
        });
    }
}
