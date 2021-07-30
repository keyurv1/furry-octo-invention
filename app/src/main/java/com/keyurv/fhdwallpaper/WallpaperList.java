package com.keyurv.fhdwallpaper;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ScrollView;
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

public class WallpaperList extends AppCompatActivity {

    RecyclerView recyclerView;
    ScrollView scrollView;
    private RequestQueue requestQueue;
    private List<Item> itemList;
    TextView label;
    AdView mAdView;


    ImageView abs,alone,animal,anime,art,bird,beach,black,bike,car,city,fantasy,flower,food,feathers,god,love,macro,nature,others,predators,space,sport,texture,vector,words,homepage,category,search;

    int pagenumber = 1;

    Boolean isscrolling = false;
    int currentItem, totalItem, scrollOutItem;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_list);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        recyclerView = findViewById(R.id.recyclerview);
        scrollView = findViewById(R.id.scrollView);

        abs = findViewById(R.id.abs);
        alone = findViewById(R.id.alone);
        animal = findViewById(R.id.animal);
        anime = findViewById(R.id.anime);
        art = findViewById(R.id.art);
        bird = findViewById(R.id.bird);
        beach = findViewById(R.id.beach);
        black = findViewById(R.id.black);
        bike = findViewById(R.id.bike);
        car = findViewById(R.id.car);
        city = findViewById(R.id.city);
        fantasy = findViewById(R.id.fantasy);
        flower = findViewById(R.id.flower);
        food = findViewById(R.id.food);
        feathers = findViewById(R.id.feathers);
        god = findViewById(R.id.god);
        love = findViewById(R.id.love);
        macro  = findViewById(R.id.macro);
        nature = findViewById(R.id.nature);
        others = findViewById(R.id.others);
        predators = findViewById(R.id.predators);
        space = findViewById(R.id.space);
        sport = findViewById(R.id.sport);
        texture = findViewById(R.id.texture);
        vector = findViewById(R.id.vector);
        words = findViewById(R.id.words);
        homepage = findViewById(R.id.homepage);
        category = findViewById(R.id.category);
        search = findViewById(R.id.search);


        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WallpaperDemo.class);
                startActivity(intent);
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WallpaperList.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchWallpaper.class);
                startActivity(intent);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);


        requestQueue = Vollysingaltone.getInstance(this).getRequestQueue();

        itemList = new ArrayList<>();

        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);



                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=abstract&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);
            }



        });



        alone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=alone&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=animal&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });


        anime.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=anime&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });

        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=art&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });

        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=bird&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });

        beach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=beach&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });


        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=black&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });

        bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=bike&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=car&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });



        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=city&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });


        fantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=fantasy&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });


        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=flower&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });


        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=food&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, "Please check your network connection and try again!!!", Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });

        feathers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=feather&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });



        god.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=god+hindu+god&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });


        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=love&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });

        macro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=macro&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });



        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=natural&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=3d&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });


        predators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q= predator&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });


        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=space&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });


        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=sport&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });



        texture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=texture&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });


        vector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=vector&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });


        words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?key=21611973-b8547d7a8c8dcb79514edaa53&q=word&image_type=photo&pretty=true&per_page=200", null, new Response.Listener<JSONObject>() {
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

                            ImageAdapter imageAdapter = new ImageAdapter(WallpaperList.this,itemList);

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
                        Toast.makeText(WallpaperList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }
        });















    }


}