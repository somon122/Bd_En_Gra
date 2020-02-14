package com.worldtechpoints.bd_english_gramar.SplashScreen;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.worldtechpoints.bd_english_gramar.MainActivity;
import com.worldtechpoints.bd_english_gramar.R;

public class OpenFragment extends Fragment {


    public OpenFragment() {
    }

    private int progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_open, container, false);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doTheWork();
                startApp();
            }
        });
        thread.start();


        return root;


    }

    private void startApp() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);


    }

    private void doTheWork() {

        for (progress = 50; progress <= 100; progress = progress + 50) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}