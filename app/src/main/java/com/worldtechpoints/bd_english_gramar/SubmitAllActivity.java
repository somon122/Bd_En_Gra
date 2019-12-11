package com.worldtechpoints.bd_english_gramar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.worldtechpoints.bd_english_gramar.Features.compositions.ComSubmitActivity;
import com.worldtechpoints.bd_english_gramar.Features.grammar.GrammarSubmitActivity;
import com.worldtechpoints.bd_english_gramar.Features.model_questions.ModelQSubmitActivity;

public class SubmitAllActivity extends AppCompatActivity {



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_all);

        Toolbar toolbar = findViewById(R.id.allSubmitToolbar_id);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Model Question Category");


    }



    public void Exercise(View view) {
        practicePanel();
    }

    public void Composition(View view) {

        Intent intent = new Intent(SubmitAllActivity.this, ComSubmitActivity.class);
        startActivity(intent);


    }

    public void Grammar(View view) {

        Intent intent = new Intent(SubmitAllActivity.this, GrammarSubmitActivity.class);
        startActivity(intent);

    }

    private void practicePanel() {


        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitAllActivity.this);
        View view1 = getLayoutInflater().inflate(R.layout.exercise_panel,null);

        Button advanceButton = view1.findViewById(R.id.advance_id);
        Button hscButton = view1.findViewById(R.id.hscBoard_id);
        Button sscButton = view1.findViewById(R.id.sscBoard_id);
        Button jscButton = view1.findViewById(R.id.jscBoard_id);


        builder.setTitle("Exercise Panel");
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });

        advanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SubmitAllActivity.this, ModelQSubmitActivity.class);
                intent.putExtra("value","All Question Exercise");
                startActivity(intent);


            }
        });
        hscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SubmitAllActivity.this, ModelQSubmitActivity.class);
                intent.putExtra("value","HSC Board Question");
                startActivity(intent);


            }
        });
        sscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SubmitAllActivity.this, ModelQSubmitActivity.class);
                intent.putExtra("value","SSC Board Question");
                startActivity(intent);

            }
        });
        jscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SubmitAllActivity.this, ModelQSubmitActivity.class);
                intent.putExtra("value","JSC Board Question");
                startActivity(intent);



            }
        });

        builder.setView(view1);
        AlertDialog dialog = builder.create();
        dialog.show();


    }




}
