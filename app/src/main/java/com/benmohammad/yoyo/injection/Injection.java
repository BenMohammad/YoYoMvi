package com.benmohammad.yoyo.injection;

import android.content.Context;

import androidx.annotation.NonNull;

import com.benmohammad.yoyo.data.source.SnippetsRepository;
import com.benmohammad.yoyo.utils.schedulers.BaseSchedulerProvider;
import com.benmohammad.yoyo.utils.schedulers.SchedulerProvider;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static SnippetsRepository provideSnippetRepository(@NonNull Context context) {
        checkNotNull(context);
        return SnippetsRepository.getINSTANCE();
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getINSTANCE();
    }
}
