package com.worldtechpoints.bd_english_gramar.Features.model_questions;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.worldtechpoints.bd_english_gramar.R;


public class ModelQuestionFragment extends Fragment {


    private Button advanceButton, hscButton,sscButton,jscButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_model_questions, container, false);


        advanceButton = root.findViewById(R.id.advanceRead_id);
        hscButton = root.findViewById(R.id.hscBoardRead_id);
        sscButton = root.findViewById(R.id.sscBoardRead_id);
        jscButton = root.findViewById(R.id.jscBoardRead_id);


        advanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              sent("All Question Exercise");
            }
        });
        hscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("HSC Board Question");

            }
        });
        sscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("SSC Board Question");


            }
        });
        jscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("JSC Board Question");
            }
        });


        return root;
    }

    private void sent(String value){

        Intent intent = new Intent(getContext(), ModelQShowActivity.class);
        intent.putExtra("value",value);
        startActivity(intent);
    }

}