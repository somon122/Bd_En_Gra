package com.worldtechpoints.bd_english_gramar.Features.compositions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;
import com.worldtechpoints.bd_english_gramar.R;

import java.util.Locale;

public class ComReadActivity extends AppCompatActivity {



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
        setContentView(R.layout.activity_com_read);

        Toolbar toolbar = findViewById(R.id.comReadToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fullView = findViewById(R.id.comFullReadView_id);
        titleView = findViewById(R.id.comTitleReadView_id);
        bottomNavigationView = findViewById(R.id.comBottomNavigationView);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            titleText = bundle.getString("title");
            fullText = bundle.getString("fullData");
            setTitle(titleText);
            fullView.setText(fullText);
            titleView.setText(titleText);
        }

        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.translateRead_id:
                       translate(fullText);
                        return true;

                    case R.id.pronunciationRead_id:

                        t1.speak(fullText, TextToSpeech.QUEUE_FLUSH, null);
                        return true;


                    case R.id.shareReadView_id:
                        shareApp(fullText,titleText);

                        return true;

                    case R.id.commentReadView_id:
                        Toast.makeText(ComReadActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();

                        return true;

                    default:
                        return false;
                }
            }
        });



    }


    private void translate(String fullText) {

        TranslateAPI translateAPI = new TranslateAPI(
                Language.AUTO_DETECT,   //Source Language
                Language.BENGALI,         //Target Language
                fullText);           //Query Text

        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String s) {
                answerDialog(s);
            }

            @Override
            public void onFailure(String s) {

                answerDialog("Sorry ! \n\n Try again");
            }
        });



    }
    private void answerDialog(String ans) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("বাংলা আনুবাদ");
        builder.setMessage(ans);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


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
