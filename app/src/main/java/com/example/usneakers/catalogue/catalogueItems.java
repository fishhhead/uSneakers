package com.example.usneakers.catalogue;

public class catalogueItems {
    private String mID;
    private int mImageResource;
    private String mText;

    public catalogueItems(String textID, int imageResource, String text) {
        this.mID = textID;
        this.mImageResource = imageResource;
        this.mText = text;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText() {
        return mText;
    }
}
