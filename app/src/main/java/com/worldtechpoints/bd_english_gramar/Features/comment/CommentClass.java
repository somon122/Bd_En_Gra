package com.worldtechpoints.bd_english_gramar.Features.comment;

public class CommentClass {

    String mCommentText;
    String mCommentTitle;
    String mTime;

    public CommentClass() {}

    public CommentClass(String mCommentText, String mCommentTitle, String mTime) {
        this.mCommentText = mCommentText;
        this.mCommentTitle = mCommentTitle;
        this.mTime = mTime;
    }

    public String getmCommentText() {
        return mCommentText;
    }

    public void setmCommentText(String mCommentText) {
        this.mCommentText = mCommentText;
    }

    public String getmCommentTitle() {
        return mCommentTitle;
    }

    public void setmCommentTitle(String mCommentTitle) {
        this.mCommentTitle = mCommentTitle;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
