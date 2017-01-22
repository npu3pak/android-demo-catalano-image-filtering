package ru.npu3pak.ImageProcessing.processing.effects;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class Effect implements Parcelable {
    public String name;

    public Effect(String name) {
        this.name = name;
    }

    public abstract Bitmap apply(Bitmap src);


    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
