package com.benmohammad.yoyo.data;

import androidx.annotation.Nullable;

import java.util.Objects;

public final class Snippet {

    @Nullable
    private final String title;

    @Nullable
    private final String code;

    @Nullable
    private final String comment;

    public Snippet(@Nullable String title, @Nullable String code, @Nullable String comment) {
        this.title = title;
        this.code = code;
        this.comment = comment;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getCode() {
        return code;
    }

    @Nullable
    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snippet snippet = (Snippet) o;
        return Objects.equals(title, snippet.title) &&
                Objects.equals(code, snippet.code) &&
                Objects.equals(comment, snippet.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, code, comment);
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
