package com.worldtechpoints.bd_english_gramar.Features.compositions;

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.worldtechpoints.bd_english_gramar.CategoryClass;
import com.worldtechpoints.bd_english_gramar.Features.model_questions.ModelAdapter;
import com.worldtechpoints.bd_english_gramar.Features.model_questions.ModelQShowActivity;
import com.worldtechpoints.bd_english_gramar.Features.model_questions.PracticeClass;
import com.worldtechpoints.bd_english_gramar.R;

import java.util.ArrayList;
import java.util.List;

public class ComShowActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    RecyclerView recyclerView;
    private List<ComPojoClass>comList;
    private ComPojoClass comPojoClass;
    private FirebaseFirestore mFirestore;
    private ComAdapter adapter;
    String subCategory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_show);

        Toolbar toolbar = findViewById(R.id.comShowToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.comRecyclerView_id);
        comList = new ArrayList<>();
        comPojoClass = new ComPojoClass();
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setHasFixedSize(true);
        mFirestore = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){

            subCategory = bundle.getString("comValue");
            exerciseRetrieve(subCategory);
            setTitle(subCategory);

        }else {
            Toast.makeText(this, "Data not found !", Toast.LENGTH_SHORT).show();
        }





    }

    private void exerciseRetrieve(String subCategory) {


        CollectionReference collectionReference = mFirestore.collection("Composition")
                .document(subCategory).collection("All_List");
        collectionReference.orderBy("mTime", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){

                            comList.clear();

                            for (DocumentSnapshot document : task.getResult()) {

                                comPojoClass = new ComPojoClass(
                                        document.getString("mComTitle"),
                                        document.getString("mComFull"),
                                        document.getString("mTime")

                                );

                                comList.add(comPojoClass);
                            }

                            adapter = new ComAdapter(ComShowActivity.this,comList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        }

                    }
                });




    }
}
