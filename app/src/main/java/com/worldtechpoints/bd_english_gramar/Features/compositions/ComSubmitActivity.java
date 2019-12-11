package com.worldtechpoints.bd_english_gramar.Features.compositions;

import androidx.annotation.NonNull;
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
import com.worldtechpoints.bd_english_gramar.Features.model_questions.ModelQSubmitActivity;
import com.worldtechpoints.bd_english_gramar.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComSubmitActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private Button submitButton;
    private EditText compositionTitleET,compositionFullET;
    private Spinner categorySpinner;

    private CategoryClass categoryClass;
    private String mainCategory;

    private List<String>stringList;
    private ProgressDialog dialog;

    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_submit);

        Toolbar toolbar = findViewById(R.id.compositionToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Composition Submit");
        mFirestore = FirebaseFirestore.getInstance();
        dialog = new ProgressDialog(this);


        submitButton = findViewById(R.id.compositionSubmitButton_id);
        compositionTitleET = findViewById(R.id.compositionTitle_id);
        compositionFullET = findViewById(R.id.compositionFull_id);
        categorySpinner = findViewById(R.id.compositionSpinner_id);
        categoryClass = new CategoryClass();
        stringList = categoryClass.compositionCategory();

        ArrayAdapter<String> mainDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringList);
        mainDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(mainDataAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mainCategory = categorySpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                compositionSubmit();

            }
        });


    }

    private void compositionSubmit() {


        String composition = compositionFullET.getText().toString();
        String title = compositionTitleET.getText().toString();


        if (composition.isEmpty()){

            compositionTitleET.setError("Enter Title");
        }if (title.isEmpty()){

            compositionFullET.setError("Enter Full description");
        }else {

            dialog.show();
            dialog.setMessage("Uploading....");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            String currentDateAndTime = sdf.format(new Date());

            Map<String,Object> questionPost= new HashMap<>();
            questionPost.put("mComTitle",title);
            questionPost.put("mComFull",composition);
            questionPost.put("mTime",currentDateAndTime);


            mFirestore.collection("Composition").document(mainCategory).collection("All_List").add(questionPost)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {

                                dialog.dismiss();
                                compositionFullET.setText("");
                                compositionTitleET.setText("");
                                Toast.makeText(ComSubmitActivity.this, "Upload successfully", Toast.LENGTH_SHORT).show();

                            }else {
                                dialog.dismiss();
                                Toast.makeText(ComSubmitActivity.this, "Upload Field", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(ComSubmitActivity.this, "Upload Field", Toast.LENGTH_SHORT).show();

                }
            });





        }



    }
}
