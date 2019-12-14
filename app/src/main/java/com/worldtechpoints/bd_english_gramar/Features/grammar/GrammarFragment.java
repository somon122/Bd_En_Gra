package com.worldtechpoints.bd_english_gramar.Features.grammar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.worldtechpoints.bd_english_gramar.R;

public class GrammarFragment extends Fragment {


    private Button sentenceButton,partsOfSpeechButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_grammars, container, false);

        sentenceButton = root.findViewById(R.id.grammar_Sentence_id);
        partsOfSpeechButton = root.findViewById(R.id.grammar_PartsOfSpeech_id);


        sentenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Sentence");

            }
        });

        partsOfSpeechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Parts_of_Speech");

            }
        });
        partsOfSpeechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Parts_of_Speech");

            }
        });


        return root;
    }


    private void sent (String value){

        Intent intent = new Intent(getContext(),GrammarShowActivity.class);
        intent.putExtra("value",value);
        startActivity(intent);

    }
}