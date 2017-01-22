package ru.npu3pak.ImageProcessing.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    private Context context;

    public FileUtils(Context context) {
        this.context = context;
    }

    public Bitmap getBitmapFromUri(Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean storeImage(Bitmap imageData, Uri uri) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(uri.getPath());
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            imageData.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            Log.w("IMAGE_PROCESSING", "Error saving image file: " + e.getMessage());
            return false;
        } catch (IOException e) {
            Log.w("IMAGE_PROCESSING", "Error saving image file: " + e.getMessage());
            return false;
        }
        return true;
    }
}
