package com.worldtechpoints.bd_english_gramar.Features.model_questions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;
import com.worldtechpoints.bd_english_gramar.Features.comment.CommentFragment;
import com.worldtechpoints.bd_english_gramar.Features.grammar.GrammarReadActivity;
import com.worldtechpoints.bd_english_gramar.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {

    private Context context;
    private List<PracticeClass>practiceClassList;
    private PracticeClass practiceClass;
    private  TextToSpeech t1;
    private FirebaseFirestore mFirestore;

    public ModelAdapter(Context context, List<PracticeClass> practiceClassList) {
        this.context = context;
        this.practiceClassList = practiceClassList;
    }

    @NonNull
    @Override
    public ModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.model_exercise,parent,false);

        speech2();
        mFirestore = FirebaseFirestore.getInstance();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ModelAdapter.ViewHolder holder, final int position) {


        practiceClass = practiceClassList.get(position);
        holder.textView.setText(practiceClass.getmQuestion());
        holder.linearLayout.setVisibility(View.GONE);
        holder.progressBar.setVisibility(View.GONE);

        holder.commentNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.linearLayout.setVisibility(View.GONE);
            }
        });
        holder.commentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentSentMethod(holder);
            }
        });

        holder.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId())
                {
                    case R.id.practiceTranslateRead_id:
                        practiceClass = practiceClassList.get(position);
                        translate(practiceClass.getmQuestion());
                        return true;

                    case R.id.practicePronunciationRead_id:

                        practiceClass = practiceClassList.get(position);
                        speech(practiceClass.getmQuestion());
                        return true;

                    case R.id.practiceAnsReadView_id:

                        practiceClass = practiceClassList.get(position);
                        answerDialog(practiceClass.getmAnswer());
                        return true;

                    case R.id.practiceShareReadView_id:

                        practiceClass = practiceClassList.get(position);
                        shareApp(practiceClass.getmQuestion(),practiceClass.getmAnswer());
                        return true;

                    case R.id.practiceCommentReadView_id:

                        holder.linearLayout.setVisibility(View.VISIBLE);

                        return true;

                    default:
                        return false;
                }
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
    private void translate(String fullText) {

        TranslateAPI translateAPI = new TranslateAPI(
                Language.AUTO_DETECT,   //Source Language
                Language.BENGALI,         //Target Language
                fullText);           //Query Text

        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String s) {
                answerDialog(s);
            }

            @Override
            public void onFailure(String s) {

                answerDialog("Sorry ! \n\n Try again");
            }
        });



    }
    private void commentSentMethod(final ViewHolder holder) {


        String comment = holder.commentBody.getText().toString();
        String cTitle = holder.commentTitle.getText().toString();

        if (comment.isEmpty()){
            holder.commentBody.setError("Please enter comment!");
        } if (cTitle.isEmpty()){
            holder.commentTitle.setError("Please enter Title!");
        }else {

            holder.progressBar.setVisibility(View.VISIBLE);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            String currentDateAndTime = sdf.format(new Date());

            Map<String,Object> commentPost= new HashMap<>();
            commentPost.put("mCommentText",comment);
            commentPost.put("mCommentTitle",cTitle);
            commentPost.put("mTime",currentDateAndTime);

            mFirestore.collection("UserComments").add(commentPost)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            if (task.isSuccessful()){
                                holder.progressBar.setVisibility(View.GONE);
                                holder.linearLayout.setVisibility(View.GONE);

                            }else {
                                holder.progressBar.setVisibility(View.GONE);
                                holder.linearLayout.setVisibility(View.VISIBLE);
                                Toast.makeText(context, "Please Connect your net", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    holder.progressBar.setVisibility(View.GONE);
                    holder.linearLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(context, "Please Connect your net", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }


    private void shareApp(String q_Value,String ansValue) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = "Question:-\n"+q_Value +"\n\nAnswer:-\n"+ansValue;
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


        private TextView textView;
        private BottomNavigationView bottomNavigationView;
        private Button commentSubmit,commentNotNow;
        private EditText commentTitle,commentBody;
        private ProgressBar progressBar;
        private LinearLayout linearLayout;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.modelViewQuestion_id);
            bottomNavigationView = itemView.findViewById(R.id.advanceBottomNavigationView);
            commentBody = itemView.findViewById(R.id.practiceCommentBody_id);
            commentTitle = itemView.findViewById(R.id.practiceCommentTitle_id);
            commentSubmit = itemView.findViewById(R.id.practiceCommentSentButton_id);
            commentNotNow = itemView.findViewById(R.id.practiceCommentNotNowButton_id);

            progressBar = itemView.findViewById(R.id.practiceCommentProgressBar_id);
            linearLayout = itemView.findViewById(R.id.practiceCommentBoxSection_id);



        }
    }
}
