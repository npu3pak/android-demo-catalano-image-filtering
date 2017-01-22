package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.Concurrent.Filters.Grayscale;
import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.GaussianBlur;
import Catalano.Imaging.Filters.ZhangSuenThinning;
import android.graphics.Bitmap;
import android.os.Parcel;

public class Thinning extends Effect {
    public Thinning(int previewDrawableId, String name) {
        super(name);
    }

    public Thinning() {
        super("Скелетизация (Zhang Suen Thinning)");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new Grayscale().applyInPlace(fastBitmap);
        new ZhangSuenThinning().applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<Thinning> CREATOR = new Creator<Thinning>() {
        public Thinning createFromParcel(Parcel in) {
            return new Thinning();
        }

        public Thinning[] newArray(int size) {
            return new Thinning[size];
        }
    };
}
