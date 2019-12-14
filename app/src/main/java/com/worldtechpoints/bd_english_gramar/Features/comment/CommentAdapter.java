package com.worldtechpoints.bd_english_gramar.Features.comment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.worldtechpoints.bd_english_gramar.R;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {


    private Context context;
    private List<CommentClass>commentList;
    private CommentClass commentClass;

    public CommentAdapter(Context context, List<CommentClass> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.comment_model_view,parent,false);

        return new CommentAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, final int position) {

        commentClass = commentList.get(position);

        holder.textView.setText(commentClass.getmCommentText());
        holder.commentTitle.setText(commentClass.getmCommentTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentClass = commentList.get(position);
                showDetails(commentClass.getmCommentText(),commentClass.getmCommentTitle());
            }
        });

    }

    private void showDetails(String commentText,String title) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Comment");
            builder.setMessage(title+"\n\n"+commentText);

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
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView,commentTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.commentViewModel_id);
            commentTitle = itemView.findViewById(R.id.commentTitleViewModel_id);


        }
    }
}
