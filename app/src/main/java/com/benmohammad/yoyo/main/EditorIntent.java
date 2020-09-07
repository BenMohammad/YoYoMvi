package com.benmohammad.yoyo.main;

import androidx.annotation.Nullable;

import com.benmohammad.yoyo.base.MviIntent;
import com.google.auto.value.AutoValue;

public interface EditorIntent extends MviIntent {

    @AutoValue
    abstract class CompileInputIntent implements EditorIntent {
        @Nullable
        abstract String input();

        public static CompileInputIntent create(@Nullable String input) {
            return new AutoValue_EditorIntent_CompileInputIntent(input);
        }
    }

    @AutoValue
    abstract class DeleteInputIntent implements EditorIntent {
        abstract String input();

        public static DeleteInputIntent create(String input) {
            return new AutoValue_EditorIntent_DeleteInputIntent(input);
        }
    }

    @AutoValue
    abstract class DeleteOutputIntent implements EditorIntent {
        @Nullable
        abstract String output();

        public static DeleteOutputIntent create(String output) {
            return new AutoValue_EditorIntent_DeleteOutputIntent(output);
        }
    }

    @AutoValue
    abstract class ShareOnWhatsAppIntent implements EditorIntent {
        abstract String message();

        public static ShareOnWhatsAppIntent create(String message) {
            return new AutoValue_EditorIntent_ShareOnWhatsAppIntent(message);
        }
    }

    @AutoValue
    abstract class SearchOutputOnlineIntent implements EditorIntent {
        abstract String output();

        public static SearchOutputOnlineIntent create(String output) {
            return new AutoValue_EditorIntent_SearchOutputOnlineIntent(output);
        }
    }

    @AutoValue
    abstract class OpenSnippetsIntent implements EditorIntent {

        public static OpenSnippetsIntent create() {
            return new AutoValue_EditorIntent_OpenSnippetsIntent();
        }
    }
}
