package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.GaussianBlur;
import android.graphics.Bitmap;
import android.os.Parcel;

public class Sepia extends Effect {
    public Sepia(int previewDrawableId, String name) {
        super(name);
    }

    public Sepia() {
        super("Сепия");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new Catalano.Imaging.Filters.Sepia().applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<Sepia> CREATOR = new Creator<Sepia>() {
        public Sepia createFromParcel(Parcel in) {
            return new Sepia();
        }

        public Sepia[] newArray(int size) {
            return new Sepia[size];
        }
    };
}
