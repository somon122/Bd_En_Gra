package com.worldtechpoints.bd_english_gramar.Features.model_questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.worldtechpoints.bd_english_gramar.CategoryClass;
import com.worldtechpoints.bd_english_gramar.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelQSubmitActivity extends AppCompatActivity {


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Button submitButton;
    private EditText fullQuestionET,answerET;
    private Spinner categorySpinner;
    private CategoryClass categoryClass;
    private String subCategory;
    private String mainCategory;
    private ProgressDialog dialog;
    private FirebaseFirestore mFirestore;

    List<String> subCategoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_qsubmit);

        Toolbar toolbar = findViewById(R.id.modelToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Model Question Submit");
        mFirestore = FirebaseFirestore.getInstance();

        dialog = new ProgressDialog(this);
        subCategoryList = new ArrayList<>();

        submitButton = findViewById(R.id.modelSubmitButton_id);
        fullQuestionET = findViewById(R.id.modelFullQuestion_id);
        answerET = findViewById(R.id.modelQuestionAns_id);
        categorySpinner = findViewById(R.id.modelSpinner_id);
        categoryClass = new CategoryClass();


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            mainCategory = bundle.getString("value");
            setTitle(mainCategory);

            if (mainCategory.equals("All Question Exercise")) {

                subCategoryList = categoryClass.englishCategory();

            }
            if (mainCategory.equals("HSC Board Question")) {

                subCategoryList = categoryClass.englishCategory();

            }
            if (mainCategory.equals("SSC Board Question")) {

                subCategoryList = categoryClass.englishCategory();

            }
            if (mainCategory.equals("JSC Board Question")) {

                subCategoryList = categoryClass.englishCategory();

            }

        }




        ArrayAdapter<String> mainDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subCategoryList);
        mainDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(mainDataAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                subCategory = categorySpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(ModelQSubmitActivity.this, "NothingSelected", Toast.LENGTH_SHORT).show();
            }
        });




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modelQuestionSubmit();

            }
        });


    }

    private void modelQuestionSubmit() {


        String fullQ = fullQuestionET.getText().toString();
        String ansQ = answerET.getText().toString();


        if (fullQ.isEmpty()){

            fullQuestionET.setError("Enter Question");
        }if (ansQ.isEmpty()){

            answerET.setError("Enter Answer");
        }else {

            dialog.show();
            dialog.setMessage("Uploading....");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            String currentDateAndTime = sdf.format(new Date());

            Map<String,Object> questionPost= new HashMap<>();
            questionPost.put("mQuestion",fullQ);
            questionPost.put("mAnswer",ansQ);
            questionPost.put("mTime",currentDateAndTime);


            mFirestore.collection("Practice").document(mainCategory).collection(subCategory).add(questionPost)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()) {

                        dialog.dismiss();
                        fullQuestionET.setText("");
                        answerET.setText("");
                        Toast.makeText(ModelQSubmitActivity.this, "Upload successfully", Toast.LENGTH_SHORT).show();

                    }else {
                        dialog.dismiss();
                        Toast.makeText(ModelQSubmitActivity.this, "Upload Field", Toast.LENGTH_SHORT).show();

                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(ModelQSubmitActivity.this, "Upload Field", Toast.LENGTH_SHORT).show();

                }
            });


        }



    }
}
