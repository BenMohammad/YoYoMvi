package com.benmohammad.yoyo.data.source;

import androidx.annotation.Nullable;

import java.util.Map;

import io.reactivex.Completable;

import static com.google.common.base.Preconditions.checkNotNull;

public class SnippetsRepository implements SnippetDataSource {

    @Nullable
    private static SnippetsRepository INSTANCE;

    Map<String, String> snippets;

    private SnippetsRepository(){}

    public static void destroyINSTANCE() {
        INSTANCE = null;
    }

    public static SnippetsRepository getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new SnippetsRepository();
        }
        return INSTANCE;
    }
    @Override
    public Completable compileInput(String input) {
        checkNotNull(input);
        return Completable.complete();
    }

    @Override
    public Completable deleteInput(String input) {
        checkNotNull(input);
        return Completable.complete();
    }

    @Override
    public Completable deleteOutput(String output) {
        checkNotNull(output);
        return Completable.complete();
    }

    @Override
    public Completable shareOnWhatsApp(String message) {
        checkNotNull(message);
        return Completable.complete();
    }

    @Override
    public Completable searchOutputOnline(String output) {
        checkNotNull(output);
        return Completable.complete();
    }

    @Override
    public Completable openSnippets() {
        return Completable.complete();
    }
}
