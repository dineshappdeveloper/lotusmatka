package com.old_dummy.cc.Models;

import android.graphics.drawable.Drawable;

public class FundMethodsModel {

    Drawable drawable;

    String method;
    String notice;
    String noticeTitle;
    String videoLink;

    public FundMethodsModel(Drawable drawable, String method, String notice, String noticeTitle, String videoLink) {
        this.drawable = drawable;
        this.method = method;
        this.notice = notice;
        this.noticeTitle = noticeTitle;
        this.videoLink = videoLink;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public String getMethod() {
        return method;
    }

    public String getNotice() {
        return notice;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getVideoLink() {
        return videoLink;
    }
}
