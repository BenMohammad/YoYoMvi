package com.benmohammad.yoyo.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.benmohammad.yoyo.injection.Injection;
import com.benmohammad.yoyo.main.EditorActionProcessorHolder;
import com.benmohammad.yoyo.main.EditorViewModel;

public class SnippetViewModelFactory implements ViewModelProvider.Factory {

    private static SnippetViewModelFactory INSTANCE;
    private final Context applicationContext;
    private SnippetViewModelFactory(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static SnippetViewModelFactory getINSTANCE(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new SnippetViewModelFactory(context.getApplicationContext());
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == EditorViewModel.class) {
            return (T) new EditorViewModel(
                    new EditorActionProcessorHolder(
                            Injection.provideSnippetRepository(applicationContext),
                            Injection.provideSchedulerProvider()));
        }
        throw new IllegalArgumentException("Unknown model class: " + modelClass);
    }
}
