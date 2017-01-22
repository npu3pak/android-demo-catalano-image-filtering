package ru.npu3pak.ImageProcessing.processing.analizing;

import android.graphics.Bitmap;

/**
 * Created with IntelliJ IDEA.
 * User: EVSafronov
 * Date: 29.12.13
 * Time: 17:13
 */
public class BradleyPointsMatrix extends AnalyzingMatrix {
    public BradleyPointsMatrix(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    protected boolean checkPixel(int pixel, int x, int y) {
        //TODO проверить справедливость гипотезы
        //После применения фильтра "Порог Брэдли" картинка состоит из ч и б точек.
        //Цвет белых точек - FFFFFF т.е. -1;
        return pixel != -1;
    }
}
