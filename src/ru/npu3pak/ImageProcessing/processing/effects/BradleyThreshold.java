package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.Concurrent.Filters.BradleyLocalThreshold;
import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Grayscale;
import Catalano.Imaging.Filters.SobelEdgeDetector;
import android.graphics.Bitmap;
import android.os.Parcel;

public class BradleyThreshold extends Effect {
    public BradleyThreshold(int previewDrawableId, String name) {
        super(name);
    }

    public BradleyThreshold() {
        super("Порог Брэдли");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new Grayscale().applyInPlace(fastBitmap);
        new BradleyLocalThreshold().applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<BradleyThreshold> CREATOR = new Creator<BradleyThreshold>() {
        public BradleyThreshold createFromParcel(Parcel in) {
            return new BradleyThreshold();
        }

        public BradleyThreshold[] newArray(int size) {
            return new BradleyThreshold[size];
        }
    };
}
