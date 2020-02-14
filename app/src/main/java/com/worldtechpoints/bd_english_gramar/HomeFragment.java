package com.worldtechpoints.bd_english_gramar;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.worldtechpoints.bd_english_gramar.Features.comment.CommentFragment;
import com.worldtechpoints.bd_english_gramar.Features.compositions.CompositionFragment;
import com.worldtechpoints.bd_english_gramar.Features.grammar.GrammarFragment;
import com.worldtechpoints.bd_english_gramar.Features.model_questions.ModelQuestionFragment;
import com.worldtechpoints.bd_english_gramar.Features.pronunciation.PronunciationFragment;
import com.worldtechpoints.bd_english_gramar.Features.translation.TranslatorFragment;

public class HomeFragment extends Fragment {


    private CardView grammarImage,compositionImage,exerciseImage,translatorImage,pronunciationImage,commentImage;
    private AdView adView;


    public HomeFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        grammarImage = root.findViewById(R.id.homeGrammar_id);
        compositionImage = root.findViewById(R.id.homeComposition_id);
        exerciseImage = root.findViewById(R.id.homeExercise_id);
        translatorImage = root.findViewById(R.id.homeTranslator_id);
        pronunciationImage = root.findViewById(R.id.homePronunciation_id);
        commentImage = root.findViewById(R.id.homeComment_id);

        AudienceNetworkAds.initialize(getContext());
        adView = new AdView(getContext(), getString(R.string.facebookBannerAds), AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = root.findViewById(R.id.homeBannerAds_id);
        adContainer.addView(adView);
        adView.loadAd();


        grammarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              GrammarFragment grammarFragment = new GrammarFragment();
              FragmentManager manager = getFragmentManager();
              manager.beginTransaction().replace(R.id.nav_host_fragment,grammarFragment)
              .commit();


            }
        });
        compositionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              CompositionFragment CommentFragment = new CompositionFragment();
              FragmentManager manager = getFragmentManager();
              manager.beginTransaction().replace(R.id.nav_host_fragment,CommentFragment)
              .commit();


            }
        });
        exerciseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              ModelQuestionFragment modelQuestionFragment = new ModelQuestionFragment();
              FragmentManager manager = getFragmentManager();
              manager.beginTransaction().replace(R.id.nav_host_fragment,modelQuestionFragment)
              .commit();


            }
        });
        translatorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              TranslatorFragment translatorFragment = new TranslatorFragment();
              FragmentManager manager = getFragmentManager();
              manager.beginTransaction().replace(R.id.nav_host_fragment,translatorFragment)
              .commit();


            }
        });
        pronunciationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              PronunciationFragment pronunciationFragment = new PronunciationFragment();
              FragmentManager manager = getFragmentManager();
              manager.beginTransaction().replace(R.id.nav_host_fragment,pronunciationFragment)
              .commit();


            }
        });
        commentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              CommentFragment commentFragment = new CommentFragment();
              FragmentManager manager = getFragmentManager();
              manager.beginTransaction().replace(R.id.nav_host_fragment,commentFragment)
              .commit();


            }
        });


        return root;
    }

}
