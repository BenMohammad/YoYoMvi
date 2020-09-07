package com.benmohammad.yoyo.injection;

import com.benmohammad.yoyo.utils.schedulers.BaseSchedulerProvider;
import com.benmohammad.yoyo.utils.schedulers.SchedulerProvider;

public class Injection {

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getINSTANCE();
    }
}
