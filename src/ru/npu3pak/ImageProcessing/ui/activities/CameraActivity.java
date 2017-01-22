package ru.npu3pak.ImageProcessing.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends Activity {

    public static final int REQUEST_CODE_TAKE_PHOTO = 10;
    public static final int REQUEST_CODE_SHOW_PREVIEW = 11;
    private Uri fileUri;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        takePhoto();
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }

    public Uri getOutputMediaFileUri() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "npu3pak.ImageProcessing");
        if (!mediaStorageDir.exists())
            if (!mediaStorageDir.mkdirs())
                return null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        return Uri.fromFile(file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK)
            showPreview();
        else
            finish();
    }

    private void showPreview() {
        Intent intent = new Intent(this, CameraPreviewActivity.class);
        intent.putExtra(CameraPreviewActivity.INTENT_KEY_PHOTO_URL, fileUri);
        startActivityForResult(intent, REQUEST_CODE_SHOW_PREVIEW);
        finish();
    }
}