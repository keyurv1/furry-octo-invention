package com.keyurv.fhdwallpaper;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Vollysingaltone {

    private RequestQueue requestQueue;
    private static Vollysingaltone mInstance;

    private Vollysingaltone(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());

    }
    public static synchronized Vollysingaltone getInstance(Context context){
        if (mInstance == null){
            mInstance = new Vollysingaltone(context);

        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}

