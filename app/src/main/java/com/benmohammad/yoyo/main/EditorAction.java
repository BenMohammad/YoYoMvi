package com.benmohammad.yoyo.main;

import androidx.annotation.Nullable;

import com.benmohammad.yoyo.base.MviAction;
import com.google.auto.value.AutoValue;

public interface EditorAction extends MviAction {

    @AutoValue
    abstract class CompileInput implements EditorAction {
        @Nullable
        abstract String input();

        public static CompileInput create(@Nullable String input) {
            return new AutoValue_EditorAction_CompileInput(input);
        }
    }

    @AutoValue
    abstract class DeleteInput implements EditorAction {
        abstract String input();

        public static DeleteInput create(String input) {
            return new AutoValue_EditorAction_DeleteInput(input);
        }
    }

    @AutoValue
    abstract class DeleteOutput implements EditorAction {
        @Nullable
        abstract String output();

        public static DeleteOutput create(String output) {
            return new AutoValue_EditorAction_DeleteOutput(output);
        }
    }

    @AutoValue
    abstract class ShareOnWhatsApp implements EditorAction {
        abstract String message();

        public static ShareOnWhatsApp create(String message) {
            return new AutoValue_EditorAction_ShareOnWhatsApp(message);
        }
    }

    @AutoValue
    abstract class SearchOutputOnline implements EditorAction {
        abstract String output();

        public static SearchOutputOnline create(String output) {
            return new AutoValue_EditorAction_SearchOutputOnline(output);
        }
    }

    @AutoValue
    abstract class OpenSnippets implements EditorAction {

        public static OpenSnippets create() {
            return new AutoValue_EditorAction_OpenSnippets();
        }
    }
}
