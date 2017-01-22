package ru.npu3pak.ImageProcessing.processing.editing;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageEditor {
    public static Bitmap rotate(Bitmap src, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    public static Bitmap resize(Bitmap src, int width, int height) {
        return Bitmap.createScaledBitmap(src, width, height, true);
    }

    public static Bitmap fit(Bitmap src, int width, int height) {
        double srcHeight = src.getHeight();
        double srcWidth = src.getWidth();
        double aspect = srcWidth / srcHeight;
        double newWidth;
        double newHeight;
        if (srcHeight > srcWidth) { //Портретная ориентация фото
            newHeight = height;
            newWidth = aspect * newHeight;
        } else { //Ландшафтная ориентация
            newWidth = width;
            newHeight = width / aspect;
        }
        return resize(src, (int) newWidth, (int) newHeight);
    }
}
