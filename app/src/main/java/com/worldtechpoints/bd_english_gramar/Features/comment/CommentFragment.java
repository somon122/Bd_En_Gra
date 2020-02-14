package com.worldtechpoints.bd_english_gramar.Features.comment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.worldtechpoints.bd_english_gramar.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommentFragment extends Fragment {


    public CommentFragment() {}

    private EditText commentET,commentTitle;
    private Button commentSent,commentBoxShow,commentNotNow;
    private RecyclerView recyclerView;
    private CommentClass commentClass;
    private CommentAdapter adapter;
    private List<CommentClass>commentList;
    private FirebaseFirestore mFirestore;
    private LinearLayout commentBoxSection;
    private ProgressBar progressBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_comment, container, false);


        commentClass = new CommentClass();

        commentET = root.findViewById(R.id.commentFullT_id);
        commentTitle = root.findViewById(R.id.commentTitle_id);
        commentSent = root.findViewById(R.id.commentSentButton_id);
        commentBoxShow = root.findViewById(R.id.commentBoxShowButton_id);
        commentNotNow = root.findViewById(R.id.commentNotNowButton_id);
        recyclerView = root.findViewById(R.id.commentRecyclerView_id);
        commentBoxSection = root.findViewById(R.id.commentBoxSection_id);
        commentBoxSection.setVisibility(View.GONE);
        progressBar = root.findViewById(R.id.commentProgressBar_id);

        commentList = new ArrayList<>();
        mFirestore = FirebaseFirestore.getInstance();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        commentRetrieve();



    commentSent.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        commentSentMethod();

            }
        });

    commentBoxShow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            commentBoxSection.setVisibility(View.VISIBLE);
            commentBoxShow.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }
    });

    commentNotNow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            CommentFragment commentFragment = new CommentFragment();
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction().replace(R.id.nav_host_fragment,commentFragment)
                    .commit();
        }
    });

        return root;
    }

    private void commentRetrieve() {

        CollectionReference collectionReference =mFirestore.collection("UserComments");

        collectionReference.orderBy("mTime", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    commentList.clear();

                    for (DocumentSnapshot document : task.getResult()) {

                        commentClass = new CommentClass(
                                document.getString("mCommentText"),
                                document.getString("mCommentTitle"),
                                document.getString("mTime")

                        );

                        commentList.add(commentClass);
                    }
                    progressBar.setVisibility(View.GONE);
                    adapter = new CommentAdapter(getContext(),commentList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


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
                       CommentFragment commentFragment = new CommentFragment();
                        FragmentManager manager = getFragmentManager();
                        manager.beginTransaction().replace(R.id.nav_host_fragment,commentFragment)
                                .commit();

                    }else {
                        progressBar.setVisibility(View.GONE);
                        commentBoxShow.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "Please Connect your net", Toast.LENGTH_SHORT).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    commentBoxShow.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Please Connect your net", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }

}
