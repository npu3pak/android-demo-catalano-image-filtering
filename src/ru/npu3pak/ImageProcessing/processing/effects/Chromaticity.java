package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.RGChromaticity;
import android.graphics.Bitmap;
import android.os.Parcel;

public class Chromaticity extends Effect {
    public Chromaticity(int previewDrawableId, String name) {
        super(name);
    }

    public Chromaticity() {
        super("Цветовой состав (Хроматичность)");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new RGChromaticity().applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<Chromaticity> CREATOR = new Creator<Chromaticity>() {
        public Chromaticity createFromParcel(Parcel in) {
            return new Chromaticity();
        }

        public Chromaticity[] newArray(int size) {
            return new Chromaticity[size];
        }
    };
}
