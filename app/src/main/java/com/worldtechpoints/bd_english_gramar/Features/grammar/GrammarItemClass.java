package com.worldtechpoints.bd_english_gramar.Features.grammar;

public class GrammarItemClass {

    String mTitle;
    String mFullDescription;
    String mTime;

    public GrammarItemClass() {}

    public GrammarItemClass(String mTitle, String mFullDescription, String mTime) {
        this.mTitle = mTitle;
        this.mFullDescription = mFullDescription;
        this.mTime = mTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmFullDescription() {
        return mFullDescription;
    }

    public void setmFullDescription(String mFullDescription) {
        this.mFullDescription = mFullDescription;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
