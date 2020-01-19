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

public class GrammarFragment extends Fragment{


    private Button sentenceButton,partsOfSpeechButton,nounButton,pronounButton,adjectiveButton,verbButton,prepositionButton,conjunctionButton;
    private Button interjectionButton,tenseButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_grammars, container, false);

        sentenceButton = root.findViewById(R.id.grammar_Sentence_id);
        partsOfSpeechButton = root.findViewById(R.id.grammar_PartsOfSpeech_id);

        nounButton = root.findViewById(R.id.grammar_Noun_id);
        pronounButton = root.findViewById(R.id.grammar_Pronoun_id);
        adjectiveButton = root.findViewById(R.id.grammar_Adjective_id);
        verbButton = root.findViewById(R.id.grammar_Verb_id);
        prepositionButton = root.findViewById(R.id.grammar_Preposition_id);
        conjunctionButton = root.findViewById(R.id.grammar_Conjunction_id);
        interjectionButton = root.findViewById(R.id.grammar_Interjection_id);
        tenseButton = root.findViewById(R.id.grammar_Tense_id);



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
        nounButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Noun");
            }
        });
        pronounButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Pronoun");
            }
        });
        adjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Adjective");
            }
        });
        verbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Verb");
            }
        });
        prepositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Preposition");
            }
        });
        conjunctionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Conjunction");
            }
        });
        interjectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Interjection");
            }
        });
        tenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Tense");
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