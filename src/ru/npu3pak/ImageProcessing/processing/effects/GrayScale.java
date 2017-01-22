package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Grayscale;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class GrayScale extends Effect {
    public GrayScale(int previewDrawableId, String name) {
        super(name);
    }

    public GrayScale() {
        super("Черно-белое");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new Grayscale().applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Parcelable.Creator<GrayScale> CREATOR = new Parcelable.Creator<GrayScale>() {
        public GrayScale createFromParcel(Parcel in) {
            return new GrayScale();
        }

        public GrayScale[] newArray(int size) {
            return new GrayScale[size];
        }
    };
}
