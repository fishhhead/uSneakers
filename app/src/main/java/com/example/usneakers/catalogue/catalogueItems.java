package com.example.usneakers.catalogue;

public class catalogueItems {
    private int mImageResource;
    private String mText;

    public catalogueItems(int imageResource, String text) {
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
