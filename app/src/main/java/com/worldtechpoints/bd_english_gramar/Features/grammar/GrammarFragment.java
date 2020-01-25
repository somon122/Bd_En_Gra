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
    private Button interjectionButton,tenseButton,articles,subjectVerbAgreement,rightFormOfVerbs,theModalAuxiliaries,clause,voice,narration;
    private Button transformationOfSentence,tagQuestion,compeletingSentence,connector,suffixesAndPrefixes,capitalization,groupVerb;
    private Button idiomsAndPhrases,vocabulary,translation;


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

        articles = root.findViewById(R.id.grammar_Articles_id);
        rightFormOfVerbs = root.findViewById(R.id.grammar_Right_form_of_verbs_id);
        subjectVerbAgreement = root.findViewById(R.id.grammar_Subject_verb_agreement_id);
        narration = root.findViewById(R.id.grammar_Narration_id);
        transformationOfSentence = root.findViewById(R.id.grammar_Transformation_of_sentence_id);
        compeletingSentence = root.findViewById(R.id.grammar_Completing_Sentence_id);
        tagQuestion = root.findViewById(R.id.grammar_Tag_Question_id);
        connector = root.findViewById(R.id.grammar_Use_of_Suitable_connectors_id);
        suffixesAndPrefixes = root.findViewById(R.id.grammar_Suffixes_and_Prefixes_id);
        theModalAuxiliaries = root.findViewById(R.id.grammar_The_modal_Auxiliaries_id);
        voice = root.findViewById(R.id.grammar_Voice_id);
        clause = root.findViewById(R.id.grammar_Clause_id);
        capitalization = root.findViewById(R.id.grammar_Punctuation_and_Capitalization_id);
        groupVerb = root.findViewById(R.id.grammar_Group_Verb_id);
        idiomsAndPhrases = root.findViewById(R.id.grammar_Idioms_and_Phrases_id);
        vocabulary = root.findViewById(R.id.grammar_Vocabulary_id);
        translation = root.findViewById(R.id.grammar_Rules_of_Translation_id);



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

        articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Articles");
            }
        });
        subjectVerbAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Subject verb agreement");
            }
        });
        rightFormOfVerbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Right form of verbs");
            }
        });
        theModalAuxiliaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("The modal Auxiliaries");
            }
        });
        clause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Clause");
            }
        });
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Voice");
            }
        });
        narration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Narration");
            }
        });

        transformationOfSentence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Transformation of sentence");
            }
        });
        tagQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Tag Question");
            }
        });
        compeletingSentence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Completing Sentence");
            }
        });
        connector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Use of Suitable connectors");
            }
        });
        suffixesAndPrefixes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Suffixes and Prefixes");
            }
        });
        capitalization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Punctuation and Capitalization");
            }
        });
        groupVerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Group Verb");
            }
        });
        idiomsAndPhrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Idioms and Phrases");
            }
        });
        vocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Vocabulary");
            }
        });
        translation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Rules of Translation");
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