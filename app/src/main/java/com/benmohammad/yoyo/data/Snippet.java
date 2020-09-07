package com.benmohammad.yoyo.data;

import androidx.annotation.Nullable;

import java.util.Objects;

public final class Snippet {

    @Nullable
    private final String title;

    @Nullable
    private final String code;


    public Snippet(@Nullable String title, @Nullable String code) {
        this.title = title;
        this.code = code;

    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getCode() {
        return code;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snippet snippet = (Snippet) o;
        return Objects.equals(title, snippet.title) &&
                Objects.equals(code, snippet.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, code);
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "title='" + title + '\'' +
                ", code='" + code +
                '}';
    }
}
