package ru.npu3pak.ImageProcessing.ui.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ru.npu3pak.ImageProcessing.R;
import ru.npu3pak.ImageProcessing.processing.editing.ImageEditor;
import ru.npu3pak.ImageProcessing.processing.effects.Effect;

public class EffectPreview extends FrameLayout {
    private Effect effect;
    private int width;
    private int height;
    private ImageView previewImage;
    private TextView previewText;
    private ProgressBar progressBar;
    private IEffectsGallery gallery;

    public EffectPreview(Context context, IEffectsGallery gallery, Effect effect, Bitmap previewBitmap, int width, int height) {
        super(context);
        this.effect = effect;
        this.width = width;
        this.height = height;
        this.gallery = gallery;
        initView();
        preparePreview(previewBitmap);
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.preview_effects_gallery_item, null);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = width;
            layoutParams.height = height;
        }
        previewText = (TextView) view.findViewById(R.id.preview_effect_text_title);
        progressBar = (ProgressBar) view.findViewById(R.id.preview_effect_progressbar);
        previewImage = (ImageView) view.findViewById(R.id.preview_effect_image_preview);
        setLayoutParams(new AbsListView.LayoutParams(200, 200));
        previewImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                gallery.onEffectSelected(effect);
            }
        });
        addView(view);
    }

    private void preparePreview(Bitmap previewBitmap) {
        new PreparePreviewTask().execute(previewBitmap);
    }

    private void showPreview(Bitmap bitmap) {
        previewImage.setImageBitmap(bitmap);
        previewText.setText(effect.name);
    }

    private class PreparePreviewTask extends AsyncTask<Bitmap, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Bitmap... bitmaps) {
            Bitmap bitmap = bitmaps[0];
            bitmap = ImageEditor.fit(bitmap, width, height);
            bitmap = effect.apply(bitmap);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            showPreview(bitmap);
            progressBar.setVisibility(GONE);
        }
    }
}
