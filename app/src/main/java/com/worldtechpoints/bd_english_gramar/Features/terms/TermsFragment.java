package com.worldtechpoints.bd_english_gramar.Features.terms;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.worldtechpoints.bd_english_gramar.R;

public class TermsFragment extends Fragment {


    public TermsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_terms, container, false);



        return root;
    }

}
