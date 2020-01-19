package com.worldtechpoints.bd_english_gramar.Features.grammar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
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
import java.util.Collections;
import java.util.Comparator;
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
                    updateListUsers(grammarList);

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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.searchBar_id);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchQuestion(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchQuestion(newText);

                return false;
            }
        });


        return true;
    }

    private void searchQuestion(String recherche) {
        if (recherche.length() > 0)
            recherche = recherche.substring(0, 1).toUpperCase() + recherche.substring(1).toLowerCase();

        List<GrammarItemClass> results = new ArrayList<>();
        for(GrammarItemClass grammarItemClass : grammarList){
            if(grammarItemClass.getmTitle() != null && grammarItemClass.getmTitle().contains(recherche)){
                results.add(grammarItemClass);
            }
        }
        updateListUsers(results);
    }


    private void updateListUsers(List<GrammarItemClass> listQuestion) {

        // Sort the list by date

        Collections.sort(listQuestion, new Comparator<GrammarItemClass>() {
            @Override
            public int compare(GrammarItemClass o1, GrammarItemClass o2) {
                int res = 1;
                if (o1.getmTitle() == (o2.getmTitle())) {
                    res = -1;
                }
                return res;
            }
        });

        adapter = new GrammarAdapter(GrammarShowActivity.this,listQuestion);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


}

