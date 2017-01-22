package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.GrayWorld;
import android.graphics.Bitmap;
import android.os.Parcel;

public class ColorBalancing extends Effect {
    public ColorBalancing(int previewDrawableId, String name) {
        super(name);
    }

    public ColorBalancing() {
        super("Цветовой баланс (алгоритм Gray World)");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new GrayWorld().applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<ColorBalancing> CREATOR = new Creator<ColorBalancing>() {
        public ColorBalancing createFromParcel(Parcel in) {
            return new ColorBalancing();
        }

        public ColorBalancing[] newArray(int size) {
            return new ColorBalancing[size];
        }
    };
}
