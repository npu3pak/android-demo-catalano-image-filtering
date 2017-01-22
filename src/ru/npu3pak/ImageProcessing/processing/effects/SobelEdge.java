package ru.npu3pak.ImageProcessing.processing.effects;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Grayscale;
import Catalano.Imaging.Filters.SobelEdgeDetector;
import android.graphics.Bitmap;
import android.os.Parcel;

public class SobelEdge extends Effect {
    public SobelEdge(int previewDrawableId, String name) {
        super(name);
    }

    public SobelEdge() {
        super("Выделить края (по Собелю)");
    }

    @Override
    public Bitmap apply(Bitmap src) {
        FastBitmap fastBitmap = new FastBitmap(src);
        new Grayscale().applyInPlace(fastBitmap);
        new SobelEdgeDetector().applyInPlace(fastBitmap);
        return fastBitmap.toBitmap();
    }

    public static final Creator<SobelEdge> CREATOR = new Creator<SobelEdge>() {
        public SobelEdge createFromParcel(Parcel in) {
            return new SobelEdge();
        }

        public SobelEdge[] newArray(int size) {
            return new SobelEdge[size];
        }
    };
}
