package com.worldtechpoints.bd_english_gramar.Features.compositions;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdExtendedListener;
import com.worldtechpoints.bd_english_gramar.R;

import java.util.List;


public class ComAdapter extends RecyclerView.Adapter<ComAdapter.ViewHolder> {

    private Context context;
    private List<ComPojoClass>comList;
    private ComPojoClass comPojoClass;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;
    private int pos;

    public ComAdapter(Context context, List<ComPojoClass> comList) {
        this.context = context;
        this.comList = comList;
    }

    @NonNull
    @Override
    public ComAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.composition_model,parent,false);

        mInterstitialAd = new InterstitialAd(context, context.getString(R.string.facebookInterstitialAds));
        mRewardedVideoAd = new RewardedVideoAd(context, context.getString(R.string.facebookRewardAds));

        mRewardedVideoAd.setAdListener(new RewardedVideoAdExtendedListener() {
            @Override
            public void onRewardedVideoActivityDestroyed() {

            }

            @Override
            public void onRewardedVideoCompleted() {

                mRewardedVideoAd.loadAd();
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

            @Override
            public void onRewardedVideoClosed() {

                comPojoClass = comList.get(pos);
                Intent intent = new Intent(context,ComReadActivity.class);
                intent.putExtra("title",comPojoClass.getmComTitle());
                intent.putExtra("fullData",comPojoClass.getmComFull());
                context.startActivity(intent);

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {

            }
        });
        mRewardedVideoAd.loadAd();

        mInterstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

                comPojoClass = comList.get(pos);
                Intent intent = new Intent(context,ComReadActivity.class);
                intent.putExtra("title",comPojoClass.getmComTitle());
                intent.putExtra("fullData",comPojoClass.getmComFull());
                context.startActivity(intent);


            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {

                mInterstitialAd.loadAd();

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });

        mInterstitialAd.loadAd();


        return new ComAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ComAdapter.ViewHolder holder, final int position) {


        comPojoClass = comList.get(position);
        holder.titleTV.setText(comPojoClass.getmComTitle());
        holder.titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd.isAdLoaded()){

                    if (mRewardedVideoAd.isAdLoaded()){
                        pos = position;
                        mRewardedVideoAd.show();
                    }else {
                        pos = position;
                        mInterstitialAd.show();
                    }
                }else {

                    comPojoClass = comList.get(position);
                    Intent intent = new Intent(context,ComReadActivity.class);
                    intent.putExtra("title",comPojoClass.getmComTitle());
                    intent.putExtra("fullData",comPojoClass.getmComFull());
                    context.startActivity(intent);
                }



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
