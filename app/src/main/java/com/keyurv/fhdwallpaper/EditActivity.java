package com.keyurv.fhdwallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.zomato.photofilters.SampleFilters;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ColorOverlaySubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EditActivity extends AppCompatActivity {

    static
    {
        System.loadLibrary("NativeImageProcessor");
    }

    ImageView editPhotoView,download,set,edit,filter1,filter2,filter3,filter4,filter5,filter6,filter7,filter8,filter9,filter10;
    String imageUrl = "";
    HorizontalScrollView horizontalScrollView;
    RewardedAd mRewardedAd;

    private InterstitialAd mInterstitialAd;
    private Boolean consent = false;
    AdView adView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                loadAd();
            }
        });

startAds();


        editPhotoView = findViewById(R.id.editphotoView);
        download = findViewById(R.id.downloadWall);
        set = findViewById(R.id.setWall);
        edit = findViewById(R.id.editwall);

        horizontalScrollView = findViewById(R.id.scrollable);

        filter1 = (ImageView) findViewById(R.id.filter1);
        filter2 = (ImageView) findViewById(R.id.filter2);
        filter3 = (ImageView) findViewById(R.id.filter3);
        filter4 = (ImageView) findViewById(R.id.filter4);
        filter5 = (ImageView) findViewById(R.id.filter5);
        filter6 = (ImageView) findViewById(R.id.filter6);
        filter7 = (ImageView) findViewById(R.id.filter7);
        filter8 = (ImageView) findViewById(R.id.filter8);
        filter9 = (ImageView) findViewById(R.id.filter9);
        filter10 = (ImageView) findViewById(R.id.filter10);



        Intent intent = getIntent();
        imageUrl = intent.getStringExtra("largeImageURL");
        Glide.with(this).load(imageUrl).into(editPhotoView);

        editPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horizontalScrollView.setVisibility(View.INVISIBLE);
                download.setVisibility(View.VISIBLE);
                set.setVisibility(View.VISIBLE);
                edit.setVisibility(View.VISIBLE);
            }
        });



        filter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter myFilter = new Filter();

                myFilter.addSubFilter(new BrightnessSubFilter(30));
                myFilter.addSubFilter(new ContrastSubFilter(1.1f));
                BitmapDrawable drawable = (BitmapDrawable) editPhotoView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);


                Bitmap outputImage = myFilter.processFilter(image);
                editPhotoView.setImageBitmap(outputImage);
            }
        });


        filter7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter myFilter = new Filter();

                myFilter.addSubFilter(new ColorOverlaySubFilter(100, .2f, .2f, .0f));


                BitmapDrawable drawable = (BitmapDrawable)editPhotoView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);


                Bitmap outputImage = myFilter.processFilter(image);
                editPhotoView.setImageBitmap(outputImage);
            }
        });


        filter8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter myFilter = new Filter();

                myFilter.addSubFilter(new ContrastSubFilter(1.2f));


                BitmapDrawable drawable = (BitmapDrawable) editPhotoView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);


                Bitmap outputImage = myFilter.processFilter(image);
                editPhotoView.setImageBitmap(outputImage);
            }
        });


        filter9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter myFilter = new Filter();

                myFilter.addSubFilter(new BrightnessSubFilter(50));


                BitmapDrawable drawable = (BitmapDrawable) editPhotoView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);


                Bitmap outputImage = myFilter.processFilter(image);
                editPhotoView.setImageBitmap(outputImage);
            }
        });


        filter10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter myFilter = new Filter();

                myFilter.addSubFilter(new VignetteSubFilter(getApplicationContext(),100));


                BitmapDrawable drawable = (BitmapDrawable)editPhotoView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);


                Bitmap outputImage = myFilter.processFilter(image);
                editPhotoView.setImageBitmap(outputImage);
            }
        });

        filter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter fooFilter = SampleFilters.getBlueMessFilter();

                BitmapDrawable drawable = (BitmapDrawable)editPhotoView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);

                Bitmap outputImage = fooFilter.processFilter(image);
                editPhotoView.setImageBitmap(outputImage);
            }
        });

        filter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter fooFilter = SampleFilters.getLimeStutterFilter();

                BitmapDrawable drawable = (BitmapDrawable) editPhotoView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);

                Bitmap outputImage = fooFilter.processFilter(image);
                editPhotoView.setImageBitmap(outputImage);
            }
        });


        filter4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter fooFilter = SampleFilters.getNightWhisperFilter();

                BitmapDrawable drawable = (BitmapDrawable)editPhotoView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);

                Bitmap outputImage = fooFilter.processFilter(image);
                editPhotoView.setImageBitmap(outputImage);
            }
        });


        filter5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter fooFilter = SampleFilters.getStarLitFilter();

                BitmapDrawable drawable = (BitmapDrawable) editPhotoView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);

                Bitmap outputImage = fooFilter.processFilter(image);
                editPhotoView.setImageBitmap(outputImage);
            }
        });


        filter6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Filter fooFilter = SampleFilters.getAweStruckVibeFilter();

                BitmapDrawable drawable = (BitmapDrawable)editPhotoView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888,true);

                Bitmap outputImage = fooFilter.processFilter(image);
                editPhotoView.setImageBitmap(outputImage);
            }
        });









        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd != null){
                    mInterstitialAd.show(EditActivity.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            setImage();
                            mInterstitialAd = null;
                            startAds();


                        }
                    });
                }else {
                    setImage();
                    mInterstitialAd = null;
                    startAds();
                }
            }
        });


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRewardedAd != null) {
                    Activity activityContext = EditActivity.this;
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            downloadWallpaperImage();

                            mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {

                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    loadAd();

                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                    loadAd();

                                    mRewardedAd = null;
                                }
                            });

                        }});
                    if (!loadAd()){
                        loadAd();

                        mRewardedAd = null;
                    }

                }else {
                    loadAd();

                    mRewardedAd = null;
                    Toast.makeText(EditActivity.this, "Press again...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download.setVisibility(View.INVISIBLE);
                set.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.INVISIBLE);
                horizontalScrollView.setVisibility(View.VISIBLE);
            }
        });

    }

    private void nonPersonilizedAd() {
        Bundle networkExtrasBundle = new Bundle();
        networkExtrasBundle.putInt("rdp", 1);
        AdRequest request = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, networkExtrasBundle)
                .build();


        adView.loadAd(request);
    }

    private void createAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),WallpaperDemo.class);
        startActivity(intent);
    }


    public void downloadWallpaperImage(){
        Drawable drawable = (Drawable)editPhotoView.getDrawable();

        // Get the bitmap from drawable object
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();



        // Save image to gallery
        String savedImageURL = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                bitmap,
                "Wallpaper",
                "Wallpaper"
        );

        // Parse the gallery image url to uri
        Uri savedImageURI = Uri.parse(savedImageURL);

        // Display the saved image to ImageView
       editPhotoView.setImageURI(savedImageURI);

        // Display saved image url to TextView
        Toast.makeText(EditActivity.this, "Wallpaper Save Successfully...", Toast.LENGTH_SHORT).show();

    }


    public boolean loadAd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-6641447331341461/8332144680",
                adRequest, new RewardedAdLoadCallback(){
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        loadAd();
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d("TAG", "Ad was loaded.");
                    }
                });


        return false;
    }

    public void startAds(){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-6641447331341461/4411358773", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                mInterstitialAd = interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                mInterstitialAd = null;
            }
        });
    }










    public void setImage(){

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        Toast.makeText(EditActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
        Bitmap bitmap = ((BitmapDrawable)editPhotoView.getDrawable()).getBitmap();
        try {
            wallpaperManager.setBitmap(bitmap);
            Toast.makeText(EditActivity.this, "Wallpaper set", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
