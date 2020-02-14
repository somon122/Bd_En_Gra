package com.worldtechpoints.bd_english_gramar;

import android.app.Application;

import com.facebook.ads.AudienceNetworkAds;

public class InitFB extends Application {


        @Override
        public void onCreate() {
            super.onCreate();
            AudienceNetworkAds.initialize(this);
        }


}
