package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Mirror;
import android.graphics.Bitmap;
import android.os.Parcel;

public class VerticalMirroring extends Effect {
    public VerticalMirroring(int previewDrawableId, String name) {
        super(name);
    }

    public VerticalMirroring() {
        super("Отражение по вертикали");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new Mirror(false, true).applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<VerticalMirroring> CREATOR = new Creator<VerticalMirroring>() {
        public VerticalMirroring createFromParcel(Parcel in) {
            return new VerticalMirroring();
        }

        public VerticalMirroring[] newArray(int size) {
            return new VerticalMirroring[size];
        }
    };
}
