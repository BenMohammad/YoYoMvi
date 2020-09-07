package com.benmohammad.yoyo.main;

import androidx.annotation.Nullable;

import com.benmohammad.yoyo.base.MviViewState;
import com.google.auto.value.AutoValue;

import autovalue.shaded.com.google$.common.base.$Ascii;

@AutoValue
public abstract class EditorViewState implements MviViewState {

    abstract String title();
    abstract String code();
    abstract String comment();
    abstract boolean loading();

    @Nullable
    abstract Throwable error();

    public abstract Builder buildWith();

    static EditorViewState idle() {
        return new AutoValue_EditorViewState.Builder()
                .title("")
                .code("")
                .comment("")
                .loading(false)
                .error(null)
                .build();
    }



    @AutoValue.Builder
    static abstract class Builder {
        abstract Builder title(String title);
        abstract Builder code(String code);
        abstract Builder comment(String comment);
        abstract Builder loading(boolean loading);
        abstract Builder error(@Nullable Throwable error);
        abstract EditorViewState build();
    }
}
