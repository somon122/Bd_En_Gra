package com.worldtechpoints.bd_english_gramar.Features.model_questions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.worldtechpoints.bd_english_gramar.R;

import java.util.List;
import java.util.Locale;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {

    private Context context;
    private List<PracticeClass>practiceClassList;
    private PracticeClass practiceClass;
    private  TextToSpeech t1;

    public ModelAdapter(Context context, List<PracticeClass> practiceClassList) {
        this.context = context;
        this.practiceClassList = practiceClassList;
    }

    @NonNull
    @Override
    public ModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.model_exercise,parent,false);

        speech2();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelAdapter.ViewHolder holder, final int position) {


        practiceClass = practiceClassList.get(position);

        holder.textView.setText(practiceClass.getmQuestion());


        holder.translatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Coming soon...", Toast.LENGTH_SHORT).show();

            }
        });

        holder.speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                practiceClass = practiceClassList.get(position);
                speech(practiceClass.getmQuestion());
            }
        });

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                practiceClass = practiceClassList.get(position);

                shareApp(practiceClass.getmQuestion());

            }
        });

        holder.showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                practiceClass = practiceClassList.get(position);

                answerDialog(practiceClass.getmAnswer());

            }
        });


    }



    private void speech(String value){

        t1.speak(value, TextToSpeech.QUEUE_FLUSH, null);

    }
  private void speech2(){

      t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
          @Override
          public void onInit(int status) {
              if(status != TextToSpeech.ERROR) {
                  t1.setLanguage(Locale.UK);
              }
          }
      });


    }



    private void shareApp(String value) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = "Question\n\n"+value;
        String shareSub = "English Grammar Topics";
        intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
        intent.putExtra(Intent.EXTRA_TEXT,shareBody);
        context.startActivity(Intent.createChooser(intent,"Grammar"));

    }

    private void answerDialog(String ans) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Question Answer");
        builder.setMessage(ans);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


    }

    @Override
    public int getItemCount() {
        return practiceClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView textView;
        Button showButton,shareButton,speechButton,translatorButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.modelViewQuestion_id);
            showButton = itemView.findViewById(R.id.modelButtonQuestion_id);
            shareButton = itemView.findViewById(R.id.modelButtonShare_id);
            speechButton = itemView.findViewById(R.id.modelButtonSpeech_id);
            translatorButton = itemView.findViewById(R.id.modelButtonTranslator_id);


        }
    }
}
