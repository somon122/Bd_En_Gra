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

                Intent intent = new Intent(getContext(), ModelQShowActivity.class);
                intent.putExtra("value","All Question Exercise");
                startActivity(intent);

            }
        });
        hscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), ModelQShowActivity.class);
                intent.putExtra("value","HSC Board Question");
                startActivity(intent);

            }
        });
        sscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), ModelQShowActivity.class);
                intent.putExtra("value","SSC Board Question");
                startActivity(intent);

            }
        });
        jscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), ModelQShowActivity.class);
                intent.putExtra("value","JSC Board Question");
                startActivity(intent);


            }
        });



        return root;
    }
}