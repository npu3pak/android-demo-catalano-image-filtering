package ru.npu3pak.ImageProcessing.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import ru.npu3pak.ImageProcessing.R;
import ru.npu3pak.ImageProcessing.processing.analizing.BradleyPointsMatrix;

/**
 * Created with IntelliJ IDEA.
 * User: EVSafronov
 * Date: 29.12.13
 * Time: 17:44
 */
public class Test extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_image);
        Bitmap testBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        BradleyPointsMatrix matrix = new BradleyPointsMatrix(testBitmap);
        for (int i = 0; i < 100; i++) {
            short[] coords = matrix.getRandomPointCoordinates();
            if (coords[0] == 6)
                Log.i("PROCESSING_TEST", "" + coords[0] + ";" + coords[1]);
        }
    }
}