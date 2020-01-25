package com.worldtechpoints.bd_english_gramar.Features.grammar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;
import com.worldtechpoints.bd_english_gramar.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GrammarReadActivity extends AppCompatActivity {


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    TextView fullView,titleView,commentTitle;
    BottomNavigationView bottomNavigationView;
    TextToSpeech t1;
    String fullText;
    String titleText;

    private EditText commentET;
    private Button commentSent,commentNotNow;
    private FirebaseFirestore mFirestore;
    private LinearLayout commentBoxSection;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_read);

        Toolbar toolbar = findViewById(R.id.grammarReadToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fullView = findViewById(R.id.grammarFullReadView_id);
        titleView = findViewById(R.id.grammarTitleReadView_id);
        bottomNavigationView = findViewById(R.id.grammarBottomNavigationView);


        commentET = findViewById(R.id.grammarCommentBody_id);
        commentTitle = findViewById(R.id.grammarCommentTitle_id);
        commentSent = findViewById(R.id.grammarCommentSentButton_id);
        commentNotNow = findViewById(R.id.grammarCommentNotNowButton_id);

        commentBoxSection = findViewById(R.id.grammarCommentBoxSection_id);
        commentBoxSection.setVisibility(View.GONE);
        progressBar = findViewById(R.id.grammarCommentProgressBar_id);
        progressBar.setVisibility(View.GONE);

        mFirestore = FirebaseFirestore.getInstance();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            titleText = bundle.getString("titleValue");
            fullText = bundle.getString("decValue");
            setTitle(titleText);
            fullView.setText(fullText);
            titleView.setText(titleText);
            commentTitle.setText(titleText);
        }

        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });


        commentSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                commentSentMethod();

            }
        });
        commentNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentBoxSection.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.VISIBLE);

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
                        commentBoxSection.setVisibility(View.VISIBLE);
                        bottomNavigationView.setVisibility(View.GONE);

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

    private void shareApp(String value,String title) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = value;
        String shareSub = title;
        intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
        intent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(intent,"Composition"));

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

    private void commentSentMethod() {


        String comment = commentET.getText().toString();
        String cTitle = commentTitle.getText().toString();

        if (comment.isEmpty()){
            commentET.setError("Please enter comment!");
        } if (cTitle.isEmpty()){
            commentTitle.setError("Please enter Title!");
        }else {

            progressBar.setVisibility(View.VISIBLE);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            String currentDateAndTime = sdf.format(new Date());

            Map<String,Object> commentPost= new HashMap<>();
            commentPost.put("mCommentText",comment);
            commentPost.put("mCommentTitle",cTitle);
            commentPost.put("mTime",currentDateAndTime);

            mFirestore.collection("UserComments").add(commentPost)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            if (task.isSuccessful()){

                                progressBar.setVisibility(View.GONE);
                                commentBoxSection.setVisibility(View.GONE);
                                bottomNavigationView.setVisibility(View.VISIBLE);


                            }else {
                                progressBar.setVisibility(View.GONE);
                                commentBoxSection.setVisibility(View.VISIBLE);
                                Toast.makeText(GrammarReadActivity.this, "Please Connect your net", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    commentBoxSection.setVisibility(View.VISIBLE);
                    Toast.makeText(GrammarReadActivity.this, "Please Connect your net", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }

}
