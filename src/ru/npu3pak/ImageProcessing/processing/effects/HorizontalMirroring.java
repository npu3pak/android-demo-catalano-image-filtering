package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Grayscale;
import Catalano.Imaging.Filters.Mirror;
import android.graphics.Bitmap;
import android.os.Parcel;

public class HorizontalMirroring extends Effect {
    public HorizontalMirroring(int previewDrawableId, String name) {
        super(name);
    }

    public HorizontalMirroring() {
        super("Отражение по горизонтали");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new Mirror(true, false).applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<HorizontalMirroring> CREATOR = new Creator<HorizontalMirroring>() {
        public HorizontalMirroring createFromParcel(Parcel in) {
            return new HorizontalMirroring();
        }

        public HorizontalMirroring[] newArray(int size) {
            return new HorizontalMirroring[size];
        }
    };
}
