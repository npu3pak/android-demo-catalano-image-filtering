package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import android.graphics.Bitmap;
import android.os.Parcel;

public class SaturationCorrection extends Effect {
    public SaturationCorrection(int previewDrawableId, String name) {
        super(name);
    }

    public SaturationCorrection() {
        super("Коррекция насыщенности");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new Catalano.Imaging.Filters.SaturationCorrection().applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<SaturationCorrection> CREATOR = new Creator<SaturationCorrection>() {
        public SaturationCorrection createFromParcel(Parcel in) {
            return new SaturationCorrection();
        }

        public SaturationCorrection[] newArray(int size) {
            return new SaturationCorrection[size];
        }
    };
}
