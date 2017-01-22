package ru.npu3pak.ImageProcessing.ui.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.GridView;
import ru.npu3pak.ImageProcessing.R;
import ru.npu3pak.ImageProcessing.processing.editing.ImageEditor;
import ru.npu3pak.ImageProcessing.processing.effects.*;
import ru.npu3pak.ImageProcessing.ui.adapters.EffectsAdapter;
import ru.npu3pak.ImageProcessing.ui.components.IEffectsGallery;
import ru.npu3pak.ImageProcessing.util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EffectsGalleryActivity extends Activity implements IEffectsGallery {
    public static final String INTENT_KEY_IMAGE_URI = "ImageUrl";
    public static final String INTENT_KEY_EFFECT = "Effect";


    private GridView effectsGrid;
    private List<Effect> effects;
    private Bitmap previewBitmap;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_effects_gallery);
        effectsGrid = (GridView) findViewById(R.id.preview_effect_gallery_gridview);
        ActionBar actionBar = getActionBar();
        Uri imageUri = getIntent().getParcelableExtra(INTENT_KEY_IMAGE_URI);
        previewBitmap = ImageEditor.fit(new FileUtils(this).getBitmapFromUri(imageUri), 400, 400);
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        createEffects();
        showEffects();
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showEffects() {
        effectsGrid.setAdapter(new EffectsAdapter(this, effects, this, previewBitmap));
    }

    private void createEffects() {
        effects = new ArrayList<Effect>();
        effects.add(new Blur());
        effects.add(new Sepia());
        effects.add(new SobelEdge());
        effects.add(new GrayScale());
        effects.add(new ColorBalancing());
        effects.add(new Emboss());
        effects.add(new Sharpen());
        effects.add(new BradleyThreshold());
        effects.add(new Thinning());
        effects.add(new SaturationCorrection());
        effects.add(new Chromaticity());
        effects.add(new HorizontalMirroring());
        effects.add(new VerticalMirroring());
        effects.add(new FullMirroring());
    }


    public void onEffectSelected(Effect effect) {
        Intent data = new Intent();
        data.putExtra(INTENT_KEY_EFFECT, effect);
        setResult(RESULT_OK, data);
        finish();
    }
}