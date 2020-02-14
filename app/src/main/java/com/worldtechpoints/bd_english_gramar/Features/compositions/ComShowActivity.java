package com.worldtechpoints.bd_english_gramar.Features.compositions;

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
import android.widget.LinearLayout;
import android.widget.Toast;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.worldtechpoints.bd_english_gramar.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    private AdView adView;




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

        adView = new AdView(this, getString(R.string.facebookBannerAds), AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = findViewById(R.id.compositionBannerAds_id);
        adContainer.addView(adView);
        adView.loadAd();





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

                            updateListUsers(comList);

                        }

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

        List<ComPojoClass> results = new ArrayList<>();
        for(ComPojoClass comPojoClass : comList){
            if(comPojoClass.getmComTitle() != null && comPojoClass.getmComTitle().contains(recherche)){
                results.add(comPojoClass);
            }
        }
        updateListUsers(results);
    }


    private void updateListUsers(List<ComPojoClass> listQuestion) {

        // Sort the list by date

        Collections.sort(listQuestion, new Comparator<ComPojoClass>() {
            @Override
            public int compare(ComPojoClass o1, ComPojoClass o2) {
                int res = 1;
                if (o1.getmComTitle() == (o2.getmComTitle())) {
                    res = -1;
                }
                return res;
            }
        });

        adapter = new ComAdapter(ComShowActivity.this,listQuestion);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


}

