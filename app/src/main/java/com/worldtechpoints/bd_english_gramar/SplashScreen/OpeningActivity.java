package com.worldtechpoints.bd_english_gramar.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Window;
import android.view.WindowManager;

import com.worldtechpoints.bd_english_gramar.MainActivity;
import com.worldtechpoints.bd_english_gramar.R;

public class OpeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_opening);

        OpenFragment openFragment = new OpenFragment();
        openFragmentMethod(openFragment);


    }

    private void openFragmentMethod(OpenFragment openFragment) {

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.openHost_id, openFragment)
                .commit();

    }
}
