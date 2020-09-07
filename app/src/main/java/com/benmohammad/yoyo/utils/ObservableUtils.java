package com.benmohammad.yoyo.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class ObservableUtils {

    public static <T> Observable<T> pairWithDelay(T immediate, T delayed) {
        return Observable.timer(1, TimeUnit.SECONDS)
                .take(1)
                .map(ignored -> delayed)
                .startWith(immediate);

    }}
