package com.benmohammad.yoyo.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.benmohammad.yoyo.R;
import com.benmohammad.yoyo.utils.NumberedEditText;
import com.benmohammad.yoyo.utils.SnippetViewModelFactory;

import io.reactivex.disposables.CompositeDisposable;

public class EditorFragment extends Fragment {


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
    private CompositeDisposable disposables = new CompositeDisposable();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editor_main, container, false);
        setHasOptionsMenu(true);
        outputTV = root.findViewById(R.id.outputTV);
        progress = root.findViewById(R.id.progress);
        input = root.findViewById(R.id.codebox);
        clearOutputBtn = root.findViewById(R.id.clearoutputBtn);
        shareWhatsAppBtn = root.findViewById(R.id.shareahatsappBtn);
        searchOnChromeBtn = root.findViewById(R.id.searchoutputGoogleBtn);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, SnippetViewModelFactory.getINSTANCE(getContext())).get(EditorViewModel.class);
        disposables = new CompositeDisposable();
        bind();
    }

    private void bind(){}

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }
}
