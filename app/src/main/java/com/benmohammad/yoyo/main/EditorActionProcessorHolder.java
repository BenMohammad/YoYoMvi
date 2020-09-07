package com.benmohammad.yoyo.main;

import androidx.annotation.NonNull;

import com.benmohammad.yoyo.utils.schedulers.BaseSchedulerProvider;

import io.reactivex.ObservableTransformer;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditorActionProcessorHolder {

    @NonNull
    private BaseSchedulerProvider schedulerProvider;

    public EditorActionProcessorHolder(@NonNull BaseSchedulerProvider schedulerProvider) {
        this.schedulerProvider = checkNotNull(schedulerProvider, "Scheduler cannot be null");
    }


}
