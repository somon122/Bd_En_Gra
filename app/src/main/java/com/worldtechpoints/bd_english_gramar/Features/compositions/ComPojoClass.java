package com.worldtechpoints.bd_english_gramar.Features.compositions;

public class ComPojoClass {

    private String mComTitle;
    private String mComFull;
    private String mTime;

    public ComPojoClass() {
    }

    public ComPojoClass(String mComTitle, String mComFull, String mTime) {
        this.mComTitle = mComTitle;
        this.mComFull = mComFull;
        this.mTime = mTime;
    }

    public String getmComTitle() {
        return mComTitle;
    }

    public void setmComTitle(String mComTitle) {
        this.mComTitle = mComTitle;
    }

    public String getmComFull() {
        return mComFull;
    }

    public void setmComFull(String mComFull) {
        this.mComFull = mComFull;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
