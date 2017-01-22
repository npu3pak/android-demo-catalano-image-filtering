package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import android.graphics.Bitmap;
import android.os.Parcel;

public class Sharpen extends Effect {
    public Sharpen(int previewDrawableId, String name) {
        super(name);
    }

    public Sharpen() {
        super("Резкость");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new Catalano.Imaging.Filters.Sharpen().applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<Sharpen> CREATOR = new Creator<Sharpen>() {
        public Sharpen createFromParcel(Parcel in) {
            return new Sharpen();
        }

        public Sharpen[] newArray(int size) {
            return new Sharpen[size];
        }
    };
}
