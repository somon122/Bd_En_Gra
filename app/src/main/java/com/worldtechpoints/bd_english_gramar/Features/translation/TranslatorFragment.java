package com.worldtechpoints.bd_english_gramar.Features.translation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;
import com.worldtechpoints.bd_english_gramar.CategoryClass;
import com.worldtechpoints.bd_english_gramar.R;

import java.util.ArrayList;
import java.util.List;


public class TranslatorFragment extends Fragment {

    public TranslatorFragment() {}

    Button translateButton;
    Spinner langListSpinner;
    TextView showTV;
    EditText targetET;
    String langName;
    List<String>langNameList;
    CategoryClass categoryClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_translator, container, false);

        translateButton = root.findViewById(R.id.translateToLangButton_id);
        langListSpinner = root.findViewById(R.id.spinnerLangList_id);
        showTV = root.findViewById(R.id.translationTextShow_id);
        targetET = root.findViewById(R.id.translateLanguageET_id);
        langNameList = new ArrayList<>();
        categoryClass = new CategoryClass();
        langNameList = categoryClass.languageList();

        ArrayAdapter<String> mainDataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, langNameList);
        mainDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        langListSpinner.setAdapter(mainDataAdapter);
        langListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                langName = langListSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = targetET.getText().toString();
                if (text.isEmpty()){
                    targetET.setError("Please Enter some Text");
                }else {

                    translate(text,langName);
                }

            }
        });


       return root;
    }
    private void translate(String fullText,String name) {

        TranslateAPI translateAPI = new TranslateAPI(
                Language.AUTO_DETECT,   //Source Language
                name,         //Target Language
                fullText);           //Query Text

        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String s) {
                showTV.setText(s);

            }

            @Override
            public void onFailure(String s) {

                showTV.setText("Sorry ! \n\n Try again");
            }
        });
    }


}
