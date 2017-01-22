package ru.npu3pak.ImageProcessing.ui.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import ru.npu3pak.ImageProcessing.R;
import ru.npu3pak.ImageProcessing.processing.editing.ImageEditor;
import ru.npu3pak.ImageProcessing.processing.effects.Effect;
import ru.npu3pak.ImageProcessing.util.FileUtils;

public class CameraPreviewActivity extends Activity {
    public static final String INTENT_KEY_PHOTO_URL = "PhotoUrl";
    public static final int REQUEST_CODE_SELECT_EFFECT = 10;

    private ImageView imagePreview;
    private Bitmap bitmap;
    private Bitmap originalBitmap;
    private Uri photoUri;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_preview);
        imagePreview = (ImageView) findViewById(R.id.camera_preview_image_preview);
        photoUri = getIntent().getParcelableExtra(INTENT_KEY_PHOTO_URL);
        bitmap = createPreviewBitmap(new FileUtils(this).getBitmapFromUri(photoUri));
        originalBitmap = Bitmap.createBitmap(bitmap);
        ActionBar actionBar = getActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        showPhoto();
    }

    private Bitmap createPreviewBitmap(Bitmap src) {
        return ImageEditor.fit(src, 640, 640);
    }

    private void showPhoto() {
        imagePreview.setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.camera_preview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_camera_preview_effect_gallery:
                showEffectGallery();
                return true;
            case R.id.action_camera_preview_revert:
                revert();
                return true;
            case R.id.action_camera_preview_create_dynamic:
                createDynamicImage();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void createDynamicImage() {
        new FileUtils(this).storeImage(bitmap, photoUri);
        Intent intent = new Intent(this, DynamicImageActivity.class);
        intent.putExtra(EffectsGalleryActivity.INTENT_KEY_IMAGE_URI, photoUri);
        startActivity(intent);
    }

    private void applyEffect(Effect effect) {
        bitmap = effect.apply(bitmap);
        showPhoto();
    }

    private void revert() {
        bitmap = Bitmap.createBitmap(originalBitmap);
        showPhoto();
    }

    private void showEffectGallery() {
        Intent intent = new Intent(this, EffectsGalleryActivity.class);
        intent.putExtra(EffectsGalleryActivity.INTENT_KEY_IMAGE_URI, photoUri);
        startActivityForResult(intent, REQUEST_CODE_SELECT_EFFECT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_EFFECT && resultCode == RESULT_OK) {
            Effect selectedEffect = data.getParcelableExtra(EffectsGalleryActivity.INTENT_KEY_EFFECT);
            applyEffect(selectedEffect);
        }
    }
}