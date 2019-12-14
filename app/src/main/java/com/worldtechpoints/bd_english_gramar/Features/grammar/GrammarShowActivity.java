package com.worldtechpoints.bd_english_gramar.Features.grammar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.worldtechpoints.bd_english_gramar.Features.compositions.ComAdapter;
import com.worldtechpoints.bd_english_gramar.Features.compositions.ComPojoClass;
import com.worldtechpoints.bd_english_gramar.Features.compositions.ComShowActivity;
import com.worldtechpoints.bd_english_gramar.R;

import java.util.ArrayList;
import java.util.List;

public class GrammarShowActivity extends AppCompatActivity {


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private RecyclerView recyclerView;
    private GrammarAdapter adapter;
    private List<GrammarItemClass>grammarList;
    private GrammarItemClass grammarItemClass;
    private FirebaseFirestore mFirestore;
    private String subCategory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_show);


        Toolbar toolbar = findViewById(R.id.grammarShowToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        grammarList = new ArrayList<>();
        recyclerView = findViewById(R.id.grammarShowRecyclerView_id);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setHasFixedSize(true);
        mFirestore = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            subCategory = bundle.getString("value");
            exerciseRetrieve(subCategory);
            setTitle(subCategory);

        }else {
            Toast.makeText(this, "Data not found !", Toast.LENGTH_SHORT).show();
        }





    }

    private void exerciseRetrieve(String subCategory) {

        CollectionReference collectionReference =mFirestore.collection("English_Grammar").document(subCategory).collection("All_List");
        collectionReference.orderBy("mTime", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    grammarList.clear();

                    for (DocumentSnapshot document : task.getResult()) {

                        grammarItemClass = new GrammarItemClass(
                                document.getString("mTitle"),
                                document.getString("mFullDescription"),
                                document.getString("mTime")

                        );

                        grammarList.add(grammarItemClass);
                    }

                    adapter = new GrammarAdapter(GrammarShowActivity.this,grammarList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                }else {
                    Toast.makeText(GrammarShowActivity.this, "Data catching field !", Toast.LENGTH_SHORT).show();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(GrammarShowActivity.this, "Please Check your net connection", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
