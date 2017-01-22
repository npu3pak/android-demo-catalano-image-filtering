package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Mirror;
import android.graphics.Bitmap;
import android.os.Parcel;

public class FullMirroring extends Effect {
    public FullMirroring(int previewDrawableId, String name) {
        super(name);
    }

    public FullMirroring() {
        super("Отражение по горизонтали и вертикали");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new Mirror(true, true).applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<FullMirroring> CREATOR = new Creator<FullMirroring>() {
        public FullMirroring createFromParcel(Parcel in) {
            return new FullMirroring();
        }

        public FullMirroring[] newArray(int size) {
            return new FullMirroring[size];
        }
    };
}
