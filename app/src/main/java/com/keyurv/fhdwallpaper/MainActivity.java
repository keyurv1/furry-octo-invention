package com.keyurv.fhdwallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView internet;
    TextView retry,connctionMsg,textView;
    ConstraintLayout connection, notConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        internet = findViewById(R.id.internet);
        retry = findViewById(R.id.retry);

        textView = findViewById(R.id.textView7);
        connection = findViewById(R.id.connection);
        notConnection = findViewById(R.id.notConnection);



        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            textView.setVisibility(View.INVISIBLE);
            connection.setVisibility(View.VISIBLE);
            enterActivity();
        }
        else {
            textView.setVisibility(View.INVISIBLE);
            notConnection.setVisibility(View.VISIBLE);
        }
    }

    public void enterActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),WallpaperDemo.class));
            }
        },2000);

    }
}
