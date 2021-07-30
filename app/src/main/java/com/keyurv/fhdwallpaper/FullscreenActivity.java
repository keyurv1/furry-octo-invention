package com.keyurv.fhdwallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FullscreenActivity extends AppCompatActivity {


    ImageView photoView, click,download,edit,set;
    String imageUrl = "";
    HorizontalScrollView scrollView;
    FileOutputStream fileOutputStream ;
    RewardedAd mRewardedAd;

    private InterstitialAd mInterstitialAd;
    private Boolean consent = false;
    AdView adView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                loadAd();
            }
        });
        startAds();

        photoView = findViewById(R.id.photoView);
        click = findViewById(R.id.click);
        download = findViewById(R.id.download);
        edit = findViewById(R.id.edit);
        set = findViewById(R.id.set);
        scrollView = findViewById(R.id.scrollable);

        Intent intent = getIntent();
        imageUrl = intent.getStringExtra("largeImageURL");
        Glide.with(this).load(imageUrl).into(photoView);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.setVisibility(View.VISIBLE);

                download.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.INVISIBLE);
                set.setVisibility(View.INVISIBLE);
            }
        });


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                download.setVisibility(View.VISIBLE);
                edit.setVisibility(View.VISIBLE);
                set.setVisibility(View.VISIBLE);


            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd != null){
                    mInterstitialAd.show(FullscreenActivity.this);
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
                    Activity activityContext = FullscreenActivity.this;
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
                    Toast.makeText(FullscreenActivity.this, "Press again...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),EditActivity.class)
                        .putExtra("largeImageURL",imageUrl);
                startActivity(intent1);
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
        Drawable drawable = (Drawable)photoView.getDrawable();

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
        photoView.setImageURI(savedImageURI);

        // Display saved image url to TextView
        Toast.makeText(FullscreenActivity.this, "Wallpaper Save Successfully...", Toast.LENGTH_SHORT).show();

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

public void setImage(){

    WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
    Toast.makeText(FullscreenActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
    Bitmap bitmap = ((BitmapDrawable)photoView.getDrawable()).getBitmap();
    try {
        wallpaperManager.setBitmap(bitmap);
        Toast.makeText(FullscreenActivity.this, "Wallpaper set", Toast.LENGTH_SHORT).show();
    }catch (IOException e){
        e.printStackTrace();
    }
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

}



//  Toast.makeText(FullscreenActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
//                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
//                Bitmap bitmap = ((BitmapDrawable)photoView.getDrawable()).getBitmap();
//
//                try {
//
//                    wallpaperManager.setBitmap(bitmap);
//                    Toast.makeText(FullscreenActivity.this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
//                }catch (IOException e){
//
//                    e.printStackTrace();
//                }