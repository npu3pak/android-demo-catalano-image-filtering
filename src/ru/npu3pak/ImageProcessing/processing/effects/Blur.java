package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.GaussianBlur;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Blur extends Effect {
    public Blur(int previewDrawableId, String name) {
        super(name);
    }

    public Blur() {
        super("Размытие (по Гауссу)");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new GaussianBlur().applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Parcelable.Creator<Blur> CREATOR = new Parcelable.Creator<Blur>() {
        public Blur createFromParcel(Parcel in) {
            return new Blur();
        }

        public Blur[] newArray(int size) {
            return new Blur[size];
        }
    };
}
