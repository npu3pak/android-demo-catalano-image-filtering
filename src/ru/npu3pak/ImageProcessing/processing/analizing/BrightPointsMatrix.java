package ru.npu3pak.ImageProcessing.processing.analizing;

import android.graphics.Bitmap;

public class BrightPointsMatrix extends AnalyzingMatrix {
    private float brightnessThreshold;

    public BrightPointsMatrix(Bitmap bitmap, float brightnessThreshold) {
        super(bitmap);
        this.brightnessThreshold = brightnessThreshold;
    }

    @Override
    protected boolean checkPixel(int pixel, int x, int y) {
        return getBrightness(pixel) >= brightnessThreshold;
    }

    private static float getBrightness(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        int V = Math.max(b, Math.max(r, g));
        return (V / 255.f);
    }

}
