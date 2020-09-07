package com.benmohammad.yoyo.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.benmohammad.yoyo.base.MviResult;
import com.benmohammad.yoyo.utils.Status;
import com.google.auto.value.AutoValue;

import static com.benmohammad.yoyo.utils.Status.FAILURE;
import static com.benmohammad.yoyo.utils.Status.LOADING;
import static com.benmohammad.yoyo.utils.Status.SUCCESS;

public interface EditorResult extends MviResult {


    @AutoValue
    abstract class CompileInputResult implements EditorResult {
        @Nullable
        abstract Status status();

        @Nullable
        abstract String output();

        @Nullable
        abstract Throwable error();

        @NonNull
        public static CompileInputResult success(@NonNull String output) {
            return new AutoValue_EditorResult_CompileInputResult(SUCCESS, output, null);
        }

        @NonNull
        public static CompileInputResult failure(Throwable error) {
            return new AutoValue_EditorResult_CompileInputResult(FAILURE, null, error);
        }

        @NonNull
        public static CompileInputResult loading() {
            return new AutoValue_EditorResult_CompileInputResult(LOADING, null, null);
        }
    }


    @AutoValue
    abstract class DeleteInputResult implements EditorResult {

        @Nullable
        abstract Status status();


        @Nullable
        abstract Throwable error();

        @NonNull
        public static DeleteInputResult success() {
            return new AutoValue_EditorResult_DeleteInputResult(SUCCESS, null);
        }

        @NonNull
        public static DeleteInputResult failure(Throwable error) {
            return new AutoValue_EditorResult_DeleteInputResult(FAILURE, error);
        }

        @NonNull
        public static DeleteInputResult loading() {
            return new AutoValue_EditorResult_DeleteInputResult(LOADING, null);
        }
    }

    @AutoValue
    abstract class DeleteOutputResult implements EditorResult {

        @Nullable
        abstract Status status();


        @Nullable
        abstract Throwable error();

        @NonNull
        public static DeleteOutputResult success() {
            return new AutoValue_EditorResult_DeleteOutputResult(SUCCESS, null);
        }

        @NonNull
        public static DeleteOutputResult failure(Throwable error) {
            return new AutoValue_EditorResult_DeleteOutputResult(FAILURE, error);
        }

        @NonNull
        public static DeleteOutputResult loading() {
            return new AutoValue_EditorResult_DeleteOutputResult(LOADING, null);
        }
    }

    @AutoValue
    abstract class OpenSnippetsResult implements EditorResult {
        @Nullable abstract Status status();
        @Nullable abstract Throwable error();

        @NonNull
        public static OpenSnippetsResult success() {
            return new AutoValue_EditorResult_OpenSnippetsResult(SUCCESS, null);
        }

        @NonNull
        public static OpenSnippetsResult failure(Throwable error) {
            return new AutoValue_EditorResult_OpenSnippetsResult(FAILURE, error);
        }
    }

    @AutoValue
    abstract class SearchOutputResult implements EditorResult {

        @Nullable
        abstract Status status();


        @Nullable
        abstract Throwable error();

        @NonNull
        public static SearchOutputResult success() {
            return new AutoValue_EditorResult_SearchOutputResult(SUCCESS, null);
        }

        @NonNull
        public static SearchOutputResult failure(Throwable error) {
            return new AutoValue_EditorResult_SearchOutputResult(FAILURE, error);
        }

        @NonNull
        public static SearchOutputResult loading() {
            return new AutoValue_EditorResult_SearchOutputResult(LOADING, null);
        }
    }

    @AutoValue
    abstract class ShareOutputWhatsAppResult implements EditorResult {

        @Nullable
        abstract Status status();


        @Nullable
        abstract Throwable error();

        @NonNull
        public static ShareOutputWhatsAppResult success() {
            return new AutoValue_EditorResult_ShareOutputWhatsAppResult(SUCCESS, null);
        }

        @NonNull
        public static ShareOutputWhatsAppResult failure(Throwable error) {
            return new AutoValue_EditorResult_ShareOutputWhatsAppResult(FAILURE, error);
        }

        @NonNull
        public static ShareOutputWhatsAppResult loading() {
            return new AutoValue_EditorResult_ShareOutputWhatsAppResult(LOADING, null);
        }
    }

}
