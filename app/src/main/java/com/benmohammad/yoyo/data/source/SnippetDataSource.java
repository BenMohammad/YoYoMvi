package com.benmohammad.yoyo.data.source;

import io.reactivex.Completable;

public interface SnippetDataSource {

    Completable compileInput(String input);
    Completable deleteInput(String input);
    Completable deleteOutput(String output);
    Completable shareOnWhatsApp(String message);
    Completable searchOutputOnline(String output);
    Completable openSnippets();
}
