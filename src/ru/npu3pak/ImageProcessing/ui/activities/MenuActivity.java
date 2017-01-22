package ru.npu3pak.ImageProcessing.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import ru.npu3pak.ImageProcessing.R;
import ru.npu3pak.ImageProcessing.test.Test;

public class MenuActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }


    public void onCameraButtonClick(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void onTestButtonClick(View view) {
        Intent intent = new Intent(this, Test.class);
        startActivity(intent);
    }
}