package com.benmohammad.yoyo.main;

import androidx.lifecycle.ViewModel;

import com.benmohammad.yoyo.base.MviViewModel;

import io.reactivex.Observable;

public class EditorViewModel extends ViewModel implements MviViewModel<EditorIntent, EditorViewState> {


    @Override
    public void processIntents(Observable<EditorIntent> intents) {

    }

    @Override
    public Observable<EditorViewState> states() {
        return null;
    }
}


