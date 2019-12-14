package com.worldtechpoints.bd_english_gramar.Features.pronunciation;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.worldtechpoints.bd_english_gramar.R;

import java.util.Locale;

public class PronunciationFragment extends Fragment {


    EditText speechEText;
    Button speechButton;
    TextToSpeech t1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pronunciation, container, false);


        speechEText = root.findViewById(R.id.text_tools);
        speechButton = root.findViewById(R.id.textToSpeech_id);

        t1=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toSpeak = speechEText.getText().toString();
                Toast.makeText(getContext(), toSpeak, Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });


        return root;
    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}