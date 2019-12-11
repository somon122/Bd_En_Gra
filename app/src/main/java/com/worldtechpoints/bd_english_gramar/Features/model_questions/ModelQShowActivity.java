package com.worldtechpoints.bd_english_gramar.Features.model_questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.worldtechpoints.bd_english_gramar.CategoryClass;
import com.worldtechpoints.bd_english_gramar.R;

import java.util.ArrayList;
import java.util.List;

public class ModelQShowActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    RecyclerView recyclerView;
    Spinner spinner;
    List<PracticeClass>practiceClassList;
    List<String>subCategoryList;

    PracticeClass practiceClass;
    FirebaseFirestore mFirestore;
    CategoryClass categoryClass;

    ModelAdapter adapter;
    String mainCategory;
    String subCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_qshow);

        Toolbar toolbar = findViewById(R.id.modelShowToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        practiceClass = new PracticeClass();
        practiceClassList = new ArrayList<>();
        categoryClass = new CategoryClass();
        subCategoryList = new ArrayList<>();

        spinner = findViewById(R.id.modelQShowSpinner_id);
        recyclerView = findViewById(R.id.modelRecyclerView_id);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setHasFixedSize(true);
        mFirestore = FirebaseFirestore.getInstance();



        Bundle bundle = getIntent().getExtras();

        if (bundle != null){

            mainCategory = bundle.getString("value");
            setTitle(mainCategory);

            if (mainCategory.equals("All Question Exercise")){

                subCategoryList = categoryClass.englishCategory();

            }
            if (mainCategory.equals("HSC Board Question")){

                subCategoryList = categoryClass.englishCategory();

            }
            if (mainCategory.equals("SSC Board Question")){

                subCategoryList = categoryClass.englishCategory();

            }
            if (mainCategory.equals("JSC Board Question")){

                subCategoryList = categoryClass.englishCategory();

            }

        }

        ArrayAdapter<String> mainDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subCategoryList);
        mainDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mainDataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                subCategory = spinner.getSelectedItem().toString();
               exerciseRetrieve(mainCategory,subCategory);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    private void exerciseRetrieve(String mainValue, String subValue){


    CollectionReference collectionReference = mFirestore.collection("Practice").document(mainValue).collection(subValue);
    collectionReference.orderBy("mTime", Query.Direction.ASCENDING).get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    if (task.isSuccessful()){

                        practiceClassList.clear();

                        for (DocumentSnapshot document : task.getResult()) {

                            PracticeClass practiceClass = new PracticeClass(
                                    document.getString("mQuestion"),
                                    document.getString("mAnswer"),
                                    document.getString("mTime")

                            );

                            practiceClassList.add(practiceClass);
                        }

                        adapter = new ModelAdapter(ModelQShowActivity.this,practiceClassList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                    }

                }
            });

}


}
