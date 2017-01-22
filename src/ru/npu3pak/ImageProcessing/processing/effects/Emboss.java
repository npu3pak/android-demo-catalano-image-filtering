package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.GrayWorld;
import android.graphics.Bitmap;
import android.os.Parcel;

public class Emboss extends Effect {
    public Emboss(int previewDrawableId, String name) {
        super(name);
    }

    public Emboss() {
        super("Рельеф");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new Catalano.Imaging.Filters.Emboss().applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<Emboss> CREATOR = new Creator<Emboss>() {
        public Emboss createFromParcel(Parcel in) {
            return new Emboss();
        }

        public Emboss[] newArray(int size) {
            return new Emboss[size];
        }
    };
}
