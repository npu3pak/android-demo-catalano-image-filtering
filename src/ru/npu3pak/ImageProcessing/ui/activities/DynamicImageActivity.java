package ru.npu3pak.ImageProcessing.ui.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import ru.npu3pak.ImageProcessing.processing.analizing.AnalyzingMatrix;
import ru.npu3pak.ImageProcessing.processing.analizing.DarkPointsMatrix;

public class DynamicImageActivity extends Activity {
    public static final String INTENT_KEY_IMAGE_URI = "ImageUri";

    AnalyzingMatrix pointsMatrix;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void analyzeImage(Bitmap bitmap){
        pointsMatrix = new DarkPointsMatrix(bitmap, 0.9F);

    }

}