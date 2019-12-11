package com.worldtechpoints.bd_english_gramar.Features.compositions;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.worldtechpoints.bd_english_gramar.R;

public class CompositionFragment extends Fragment {


    private Button paragraph,letter, application,email,cv,essay,chart,dialogue,story;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_compositions, container, false);


        paragraph =root.findViewById(R.id.paragraph_id);
        letter =root.findViewById(R.id.letter_id);
        application =root.findViewById(R.id.application_id);
        email =root.findViewById(R.id.e_mail_id);
        cv =root.findViewById(R.id.cv_id);
        essay =root.findViewById(R.id.essay_id);
        chart =root.findViewById(R.id.chart_id);
        dialogue =root.findViewById(R.id.dialogue_id);
        story =root.findViewById(R.id.completingStory_id);


                paragraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Paragraph");

            }
        });
                letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Letter");

            }
        });
                application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sent("Application");

            }
        });
                email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Email");

            }
        });
                cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("CV");

            }
        });
                essay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Essay");

            }
        });
                chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Chart");

            }
        });
                dialogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Dialogue");

            }
        });
                story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Story");

            }
        });


        return root;
    }


    private void sent (String value){

        Intent intent = new Intent(getContext(),ComShowActivity.class);
        intent.putExtra("comValue",value);
        startActivity(intent);

    }
}