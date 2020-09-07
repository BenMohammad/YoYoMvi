package com.benmohammad.yoyo.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.benmohammad.yoyo.R;
import com.benmohammad.yoyo.utils.ActivityUtils;

public class EditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditorFragment editorFragment = (EditorFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(editorFragment == null) {
            editorFragment = EditorFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), editorFragment, R.id.contentFrame);
        }
    }
}