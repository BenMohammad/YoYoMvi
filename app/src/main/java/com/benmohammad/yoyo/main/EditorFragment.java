package com.benmohammad.yoyo.main;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.benmohammad.yoyo.utils.NumberedEditText;

public class EditorFragment extends Fragment  {


    public static EditorFragment newInstance() {
        return new EditorFragment();
    }

    NumberedEditText input;
    TextView outputTV;
    ProgressBar progress;
    ImageView clearOutputBtn;
    ImageView shareWhatsAppBtn;
    ImageView searchOnChromeBtn;
    EditorViewModel viewModel;

}
