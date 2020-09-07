package com.benmohammad.yoyo.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.benmohammad.yoyo.base.MviViewModel;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditorViewModel extends ViewModel implements MviViewModel<EditorIntent, EditorViewState> {


    @NonNull
    private EditorActionProcessorHolder actionProcessorHolder;

    public EditorViewModel(@NonNull EditorActionProcessorHolder actionProcessorHolder) {
        this.actionProcessorHolder = checkNotNull(actionProcessorHolder, "Processor cannot be null");

    }

    @Override
    public void processIntents(Observable<EditorIntent> intents) {

    }

    @Override
    public Observable<EditorViewState> states() {
        return null;
    }
}
