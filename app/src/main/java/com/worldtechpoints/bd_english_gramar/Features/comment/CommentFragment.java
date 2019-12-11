package com.worldtechpoints.bd_english_gramar.Features.comment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.worldtechpoints.bd_english_gramar.R;


public class CommentFragment extends Fragment {


    public CommentFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_comment, container, false);





        return root;
    }

}
