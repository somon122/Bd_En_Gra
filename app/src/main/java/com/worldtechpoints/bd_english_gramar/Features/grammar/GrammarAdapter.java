package com.worldtechpoints.bd_english_gramar.Features.grammar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.worldtechpoints.bd_english_gramar.R;

import java.util.List;

public class GrammarAdapter extends RecyclerView.Adapter<GrammarAdapter.ViewHolder> {

    private Context context;
    private List<GrammarItemClass>grammarList;
    private GrammarItemClass grammarItemClass;

    public GrammarAdapter(Context context, List<GrammarItemClass> grammarList) {
        this.context = context;
        this.grammarList = grammarList;
    }

    @NonNull
    @Override
    public GrammarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.grammar_model_view,parent,false);

        return new GrammarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GrammarAdapter.ViewHolder holder, final int position) {

        grammarItemClass = grammarList.get(position);

        holder.titleView.setText(grammarItemClass.mTitle);

        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grammarItemClass = grammarList.get(position);

                Intent intent = new Intent(context,GrammarReadActivity.class);
                intent.putExtra("titleValue",grammarItemClass.mTitle);
                intent.putExtra("decValue",grammarItemClass.mFullDescription);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {

        return grammarList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.grammarViewTitle_id);

        }
    }
}
