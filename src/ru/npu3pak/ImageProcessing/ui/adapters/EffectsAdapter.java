package ru.npu3pak.ImageProcessing.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import ru.npu3pak.ImageProcessing.R;
import ru.npu3pak.ImageProcessing.processing.effects.Effect;
import ru.npu3pak.ImageProcessing.ui.components.EffectPreview;
import ru.npu3pak.ImageProcessing.ui.components.IEffectsGallery;

import java.util.ArrayList;
import java.util.List;

public class EffectsAdapter extends ArrayAdapter<Effect> {
    private Context context;
    private List<Effect> effects;
    private IEffectsGallery gallery;
    private Bitmap previewBitmap;

    private ArrayList<EffectPreview> previews = new ArrayList<EffectPreview>();


    public EffectsAdapter(Context context, List<Effect> effects, IEffectsGallery gallery, Bitmap previewBitmap) {
        super(context, R.layout.preview_effects_gallery_item, effects);
        this.context = context;
        this.effects = effects;
        this.gallery = gallery;
        this.previewBitmap = previewBitmap;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            return previews.get(position);
        } catch (IndexOutOfBoundsException e) {
            Effect effect = effects.get(position);
            Bitmap preview = Bitmap.createBitmap(previewBitmap);
            EffectPreview effectPreview = new EffectPreview(context, gallery, effect, preview, 200, 200);
            previews.add(position, effectPreview);
            return effectPreview;
        }
    }


}
