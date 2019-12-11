package com.worldtechpoints.bd_english_gramar.Features.compositions;

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


public class ComAdapter extends RecyclerView.Adapter<ComAdapter.ViewHolder> {

    private Context context;
    private List<ComPojoClass>comList;
    private ComPojoClass comPojoClass;

    public ComAdapter(Context context, List<ComPojoClass> comList) {
        this.context = context;
        this.comList = comList;
    }

    @NonNull
    @Override
    public ComAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.composition_model,parent,false);

        return new ComAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ComAdapter.ViewHolder holder, final int position) {


        comPojoClass = comList.get(position);
        holder.titleTV.setText(comPojoClass.getmComTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comPojoClass = comList.get(position);
                Intent intent = new Intent(context,ComReadActivity.class);
                intent.putExtra("title",comPojoClass.getmComTitle());
                intent.putExtra("fullData",comPojoClass.getmComFull());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return comList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.comViewModelTitle_id);


        }
    }
}
