package ru.npu3pak.ImageProcessing.processing.analizing;

import android.graphics.Bitmap;

public abstract class AnalyzingMatrix {
    public static final int EMPTY_ELEMENT = -1;
    public static final int NOT_EMPTY_ELEMENT = 1;
    private short[][] startPoints;

    public AnalyzingMatrix(Bitmap bitmap) {
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        //Сначала сделаем матрицу с размерами, равными исходному изображению
        byte[][] pixelMatrix = new byte[bitmapHeight][bitmapWidth];
        short[] rowsSizes = new short[bitmapHeight];
        short maxRowSize = 0;
        short rowsCount = 0;
        //В местах черных точек записываем номер колонки
        for (short y = 0; y < bitmapHeight; y++) {
            for (short x = 0; x < bitmapWidth; x++) {
                if (checkPixel(bitmap.getPixel(x, y), x, y)) {
                    pixelMatrix[y][x] = NOT_EMPTY_ELEMENT;
                    rowsSizes[y]++;
                } else
                    pixelMatrix[y][x] = EMPTY_ELEMENT;
            }
            if (rowsSizes[y] > maxRowSize)
                maxRowSize = rowsSizes[y];
            if (rowsSizes[y] > 0)
                rowsCount++;
        }
        //Теперь сжимаем матрицу
        //Первый элемент ряда - его номер. Второй - его длина
        short[][] collapsedMatrix = new short[rowsCount][maxRowSize + 2];
        short collapsedX;
        short collapsedY = 0;

        for (short y = 0; y < bitmapHeight; y++) {
            if (collapsedY >= rowsCount)
                break;
            if (rowsSizes[y] > 0) {
                collapsedMatrix[collapsedY][0] = y; //Первый элемент - номер ряда
                collapsedMatrix[collapsedY][1] = rowsSizes[y]; //Первый элемент - размер ряда
                collapsedX = 2;
                for (short x = 0; x < bitmapWidth; x++) {
                    if (pixelMatrix[y][x] != EMPTY_ELEMENT) {
                        collapsedMatrix[collapsedY][collapsedX] = x;
                        collapsedX++;
                    }
                }
                collapsedY++;
            }
        }
        startPoints = collapsedMatrix;
    }

    public short[][] getPixelMatrix() {
        return startPoints;
    }

    /**
     * @return [0]-координата X. [1]-координата Y
     */
    public short[] getRandomPointCoordinates() {
        int rowsCount = startPoints.length;
        short row = (short) (Math.random() * startPoints.length);
        row = (short) Math.min(row, rowsCount - 1);
        short y = startPoints[row][0];
        short maxX = startPoints[row][1];
        short column = (short) Math.round(Math.max(2, Math.random() * maxX + 1));
        short x = startPoints[row][column];
        return new short[]{x, y};
    }

    protected abstract boolean checkPixel(int pixel, int x, int y);
}
