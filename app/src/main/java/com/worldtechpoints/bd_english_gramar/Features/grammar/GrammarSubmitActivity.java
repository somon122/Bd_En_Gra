package com.worldtechpoints.bd_english_gramar.Features.grammar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.worldtechpoints.bd_english_gramar.CategoryClass;
import com.worldtechpoints.bd_english_gramar.Features.compositions.ComSubmitActivity;
import com.worldtechpoints.bd_english_gramar.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrammarSubmitActivity extends AppCompatActivity {



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Spinner spinner;
    private EditText titleET,fullDescET;
    private FirebaseFirestore mFirestore;

    private List<String>grammarList;
    private CategoryClass categoryClass;
    private String mainCategory;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_submit);

        Toolbar toolbar = findViewById(R.id.grammarSubmitToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mFirestore = FirebaseFirestore.getInstance();
        fullDescET = findViewById(R.id.grammarSubmitFullDesc_id);
        titleET = findViewById(R.id.grammarSubmitQTitle_id);
        spinner = findViewById(R.id.grammarSubmitSpinner_id);
        categoryClass = new CategoryClass();
        grammarList = new ArrayList<>();
        dialog = new ProgressDialog(this);

        grammarList = categoryClass.grammarCategory();

        ArrayAdapter<String> mainDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, grammarList);
        mainDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mainDataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mainCategory = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    public void GrammarSubmit(View view) {

        grammarSubmit();


    }

    private void grammarSubmit() {


        String title = titleET.getText().toString();
        String fullText = fullDescET.getText().toString();


        if (title.isEmpty()){

            titleET.setError("Enter Title");
        }if (fullText.isEmpty()){

            fullDescET.setError("Enter Full description");
        }else {

            dialog.show();
            dialog.setMessage("Uploading....");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            String currentDateAndTime = sdf.format(new Date());

            Map<String,Object> grammarPost= new HashMap<>();
            grammarPost.put("mTitle",title);
            grammarPost.put("mFullDescription",fullText);
            grammarPost.put("mTime",currentDateAndTime);


            mFirestore.collection("English_Grammar").document(mainCategory).collection("All_List").add(grammarPost)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {

                                dialog.dismiss();
                                fullDescET.setText("");
                                titleET.setText("");
                                Toast.makeText(GrammarSubmitActivity.this, "Upload successfully", Toast.LENGTH_SHORT).show();

                            }else {
                                dialog.dismiss();
                                Toast.makeText(GrammarSubmitActivity.this, "Upload Field", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(GrammarSubmitActivity.this, "Upload Field", Toast.LENGTH_SHORT).show();

                }
            });





        }




    }
}
