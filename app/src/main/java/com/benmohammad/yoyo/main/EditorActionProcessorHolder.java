package com.benmohammad.yoyo.main;

import androidx.annotation.NonNull;

import com.benmohammad.yoyo.data.source.SnippetsRepository;
import com.benmohammad.yoyo.utils.schedulers.BaseSchedulerProvider;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditorActionProcessorHolder {


        @NonNull
    private SnippetsRepository snippetsRepository;

    @NonNull
    private BaseSchedulerProvider schedulerProvider;

    public EditorActionProcessorHolder(@NonNull SnippetsRepository snippetsRepository,
                                       @NonNull BaseSchedulerProvider schedulerProvider) {
        this.snippetsRepository = checkNotNull(snippetsRepository, "repository cannot be null");
        this.schedulerProvider = checkNotNull(schedulerProvider, "Scheduler cannot be null");

    }




}