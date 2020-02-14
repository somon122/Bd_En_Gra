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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
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
    String targetLang;

    private AdView adView;

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

        AudienceNetworkAds.initialize(getContext());
        adView = new AdView(getContext(), getString(R.string.facebookBannerAds), AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = root.findViewById(R.id.translationBannerAds_id);
        adContainer.addView(adView);
        adView.loadAd();


        ArrayAdapter<String> mainDataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, langNameList);
        mainDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        langListSpinner.setAdapter(mainDataAdapter);
        langListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                langName = langListSpinner.getSelectedItem().toString();

                switch (langName) {
                    case "BENGALI":
                        targetLang = "bn";
                        break;
                    case "ENGLISH":
                        targetLang = "en";
                        break;
                    case "ARABIAN":
                        targetLang = "ar";
                        break;
                    case "HINDI":
                        targetLang = "hi";
                        break;
                    case "URDU":
                        targetLang = "ur";
                        break;
                    default:
                        Toast.makeText(getContext(), "Please Select Language", Toast.LENGTH_SHORT).show();
                        break;
                }
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

                    translate(text, targetLang);
                }

            }
        });


       return root;
    }
    private void translate(String fullText,String name) {

        TranslateAPI translateAPI = new TranslateAPI(
                Language.AUTO_DETECT,
                name,
                fullText);

        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String s) {
                showTV.setText(s);

            }

            @Override
            public void onFailure(String s) {

                showTV.setText("Sorry ! \n\n Select target Language Then Try again");
            }
        });
    }


}
