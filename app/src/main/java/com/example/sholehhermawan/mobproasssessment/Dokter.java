package com.example.sholehhermawan.mobproasssessment;

/**
 * Created by Sholeh Hermawan on 3/13/2017.
 */

public class Dokter {

    private String mItemWord;
    private String mSubItemWord;
    private int mImageWord = NO_IMAGE_AVAILABLE;
    private  static final int NO_IMAGE_AVAILABLE = -1;

    public Dokter(String mItemWord, String mSubItemWord, int mImageWord) {
        this.mItemWord = mItemWord;
        this.mSubItemWord = mSubItemWord;
        this.mImageWord = mImageWord;
    }

    public String getmItemWord() {
        return mItemWord;
    }

    public String getmSubItemWord() {
        return mSubItemWord;
    }

    public int getmImageWord() {
        return mImageWord;
    }

    public String toString(){
        return getmItemWord() + " " + getmSubItemWord();
    }

    public boolean isImageAvailable(){
        return mImageWord != NO_IMAGE_AVAILABLE;
    }
}
