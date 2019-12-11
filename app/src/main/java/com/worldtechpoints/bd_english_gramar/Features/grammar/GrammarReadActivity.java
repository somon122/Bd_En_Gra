package com.worldtechpoints.bd_english_gramar.Features.grammar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.worldtechpoints.bd_english_gramar.Features.compositions.ComReadActivity;
import com.worldtechpoints.bd_english_gramar.R;

import java.util.Locale;

public class GrammarReadActivity extends AppCompatActivity {


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    TextView fullView,titleView;
    BottomNavigationView bottomNavigationView;
    TextToSpeech t1;
    String fullText;
    String titleText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_read);



        Toolbar toolbar = findViewById(R.id.comReadToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fullView = findViewById(R.id.grammarFullReadView_id);
        titleView = findViewById(R.id.grammarTitleReadView_id);
        bottomNavigationView = findViewById(R.id.grammarBottomNavigationView);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            titleText = bundle.getString("titleValue");
            fullText = bundle.getString("decValue");
            setTitle(titleText);
            fullView.setText(fullText);
            titleView.setText(titleText);
        }

        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.pronunciationRead_id){

                    t1.speak(fullText, TextToSpeech.QUEUE_FLUSH, null);

                }
                if (id == R.id.shareReadView_id){


                    shareApp(fullText,titleText);


                }
                if (id == R.id.commentReadView_id){


                    Toast.makeText(GrammarReadActivity.this, "Coming soon...", Toast.LENGTH_SHORT).show();

                }



                return false;
            }
        });



    }

    private void shareApp(String value,String title) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = value;
        String shareSub = title;
        intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
        intent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(intent,"Composition"));

    }

}
