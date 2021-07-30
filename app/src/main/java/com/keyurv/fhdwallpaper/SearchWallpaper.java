package com.keyurv.fhdwallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchWallpaper extends AppCompatActivity {

    EditText query;
    Button search,cancle;
    ConstraintLayout layout;

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Item> itemList;
    ImageView homepage,category,search1;
    int pagenumber = 1;
    AdView mAdView;

    Boolean isscrolling = false;
    int currentItem, totalItem, scrollOutItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_wallpaper);


        query = findViewById(R.id.query);
        cancle = findViewById(R.id.cancleBtn);
        search = findViewById(R.id.searchBtn);
        layout = findViewById(R.id.layout);
        recyclerView = findViewById(R.id.recyclerview);
        search1 = findViewById(R.id.search);

        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchWallpaper.class);
                startActivity(intent);
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);


        requestQueue = Vollysingaltone.getInstance(this).getRequestQueue();

        itemList = new ArrayList<>();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = query.getText().toString().toLowerCase();
                recyclerView.setVisibility(View.VISIBLE);
                layout.setVisibility(View.INVISIBLE);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q="+search+"&image_type=photo&pretty=true&per_page=200" , null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i= 0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String imageUrl = jsonObject.getString("largeImageURL");



                                Item post = new Item(imageUrl);
                                itemList.add(post);
                            }

                            ImageAdapter imageAdapter = new ImageAdapter(SearchWallpaper.this,itemList);

                            recyclerView.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            pagenumber++;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchWallpaper.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);


            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.setText("");
            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), WallpaperDemo.class);
        startActivity(intent);
    }
}
