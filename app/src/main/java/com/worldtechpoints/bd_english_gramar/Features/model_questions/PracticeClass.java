package com.worldtechpoints.bd_english_gramar.Features.model_questions;

public class PracticeClass {

    private String mQuestion;
    private String mAnswer;
    private String mTime;

    public PracticeClass() {}

    public PracticeClass(String mQuestion, String mAnswer, String mTime) {
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
        this.mTime = mTime;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
