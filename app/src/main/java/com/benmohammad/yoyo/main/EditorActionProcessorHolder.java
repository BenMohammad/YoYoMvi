package com.benmohammad.yoyo.main;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.benmohammad.yoyo.data.source.SnippetsRepository;
import com.benmohammad.yoyo.utils.schedulers.BaseSchedulerProvider;

import autovalue.shaded.com.google$.common.collect.$AbstractIterator;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

import static com.benmohammad.yoyo.utils.ObservableUtils.pairWithDelay;
import static com.google.common.base.Preconditions.checkNotNull;

public class EditorActionProcessorHolder {


        @NonNull
    private SnippetsRepository snippetsRepository;

    @NonNull
    private BaseSchedulerProvider schedulerProvider;

    public EditorActionProcessorHolder(@NonNull SnippetsRepository snippetsRepository,
                                       @NonNull BaseSchedulerProvider schedulerProvider) {
        this.snippetsRepository = checkNotNull(snippetsRepository, "repository cannot be null");
        this.schedulerProvider = checkNotNull(schedulerProvider, "Scheduler cannot be null");

    }

    private ObservableTransformer<EditorAction.CompileInput, EditorResult.CompileInputResult> compileProcessor =
            actions -> actions.flatMap(action ->
                    snippetsRepository.compileInput(action.input())
                            .toObservable()
                            .map(string -> EditorResult.CompileInputResult.success(string.toString()))
                            .onErrorReturn(EditorResult.CompileInputResult::failure)
                            .subscribeOn(schedulerProvider.io())
                            .observeOn(schedulerProvider.ui())
                            .startWith(EditorResult.CompileInputResult.loading()));

    private ObservableTransformer<EditorAction.DeleteInput, EditorResult.DeleteInputResult> deleteInputProcessor =
            actions -> actions.flatMap(action ->
                    snippetsRepository.deleteInput(action.input())
                            .toObservable()
                            .map(string -> EditorResult.DeleteInputResult.success())
                            .onErrorReturn(EditorResult.DeleteInputResult::failure)
                            .subscribeOn(schedulerProvider.io())
                            .observeOn(schedulerProvider.ui())
                            .startWith(EditorResult.DeleteInputResult.loading()));

    private ObservableTransformer<EditorAction.DeleteOutput, EditorResult.DeleteOutputResult> deleteOutputProcessor =
            actions -> actions.flatMap(action ->
                    snippetsRepository.deleteOutput(action.output())
                            .toObservable()
                            .map(string -> EditorResult.DeleteOutputResult.success())
                            .onErrorReturn(EditorResult.DeleteOutputResult::failure)
                            .subscribeOn(schedulerProvider.io())
                            .observeOn(schedulerProvider.ui())
                            .startWith(EditorResult.DeleteOutputResult.loading()));

    private ObservableTransformer<EditorAction.OpenSnippets, EditorResult.OpenSnippetsResult> openSnippetsProcessor =
            actions -> actions.flatMap(action ->
                    snippetsRepository.openSnippets()
                            .toObservable()
                            .map(string -> EditorResult.OpenSnippetsResult.success())
                            .onErrorReturn(EditorResult.OpenSnippetsResult::failure)
                            .subscribeOn(schedulerProvider.io())
                            .observeOn(schedulerProvider.ui())
                            .startWith(EditorResult.OpenSnippetsResult.success()));

    private ObservableTransformer<EditorAction.SearchOutputOnline, EditorResult.SearchOutputResult> searchGoogleProcessor =
            actions -> actions.flatMap(action ->
                    snippetsRepository.searchOutputOnline(action.output())
                            .toObservable()
                            .map(string -> EditorResult.SearchOutputResult.success())
                            .onErrorReturn(EditorResult.SearchOutputResult::failure)
                            .subscribeOn(schedulerProvider.io())
                            .observeOn(schedulerProvider.ui())
                            .startWith(EditorResult.SearchOutputResult.success()));

    private ObservableTransformer<EditorAction.ShareOnWhatsApp, EditorResult.ShareOutputWhatsAppResult> shareOnWhatsAppProcessor =
            actions -> actions.flatMap(action ->
                    snippetsRepository.shareOnWhatsApp(action.message())
                            .toObservable()
                            .map(string -> EditorResult.ShareOutputWhatsAppResult.success())
                            .onErrorReturn(EditorResult.ShareOutputWhatsAppResult::failure)
                            .subscribeOn(schedulerProvider.io())
                            .observeOn(schedulerProvider.ui())
                            .startWith(EditorResult.ShareOutputWhatsAppResult.success()));

    ObservableTransformer<EditorAction, EditorResult> actionProcessor =
            actions -> actions.publish(shared -> Observable.merge(
                    shared.ofType(EditorAction.CompileInput.class).compose(compileProcessor),
                    shared.ofType(EditorAction.DeleteInput.class).compose(deleteInputProcessor),
                    shared.ofType(EditorAction.DeleteOutput.class).compose(deleteOutputProcessor),
                    shared.ofType(EditorAction.ShareOnWhatsApp.class).compose(shareOnWhatsAppProcessor))
                    .mergeWith(
                            shared.filter(v -> !(v instanceof EditorAction.CompileInput)
                                    && !(v instanceof EditorAction.DeleteInput)
                                    && !(v instanceof EditorAction.DeleteOutput)
                                    && !(v instanceof EditorAction.OpenSnippets)
                                    && !(v instanceof EditorAction.SearchOutputOnline)
                                    && !(v instanceof EditorAction.ShareOnWhatsApp)

                            )
                                    .flatMap(w -> Observable.error(
                                            new IllegalArgumentException("Unknown Action type: " + w)
                                    ))));






}
//
//
//
//    @NonNull
//    private SnippetsRepository snippetsRepository;
//
//    @NonNull
//    private BaseSchedulerProvider schedulerProvider;
//
//    public EditorActionProcessorHolder(@NonNull SnippetsRepository snippetsRepository,
//                                       @NonNull BaseSchedulerProvider schedulerProvider) {
//        this.snippetsRepository = checkNotNull(snippetsRepository, "repository cannot be null");
//        this.schedulerProvider = checkNotNull(schedulerProvider, "Scheduler cannot be null");
//
//    }
//
////    private ObservableTransformer<EditorAction.CompileInput, EditorResult.CompileInputResult> compileInputProcessor =
////            actions -> actions.flatMap(action ->
////                    snippetsRepository.compileInput(action.input())
////                            .andThen(Observable.just(EditorResult.CompileInputResult.success(action.input())))
////                            .flatMap(snippet ->
////                                    pairWithDelay(
////                                            EditorResult.CompileInputResult.success(snippet.output()),
////                                            EditorResult.CompileInputResult.loading()
////                                    )
////                            )
////                            .onErrorReturn(EditorResult.CompileInputResult::failure)
////                            .subscribeOn(schedulerProvider.io()))
////                    .observeOn(schedulerProvider.ui())
////                    .startWith(EditorResult.CompileInputResult.loading());
////
////    private ObservableTransformer<EditorAction.DeleteInput, EditorResult.DeleteInputResult> deleteInputProcessor =
////            actions -> actions.flatMap(action ->
////                    snippetsRepository.deleteInput(action.input())
////                            .andThen(Observable.just(EditorResult.DeleteInputResult.success()))
////                            .flatMap(snippet ->
////                                    pairWithDelay(
////                                            EditorResult.DeleteInputResult.success(),
////                                            EditorResult.DeleteInputResult.loading()
////                                    )
////                            )
////                            .onErrorReturn(EditorResult.DeleteInputResult::failure)
////                            .subscribeOn(schedulerProvider.io())
////                            .observeOn(schedulerProvider.ui())
////                            .startWith(EditorResult.DeleteInputResult.loading()));
////
////    private ObservableTransformer<EditorAction.DeleteOutput, EditorResult.DeleteOutputResult> deleteOutputProcessor =
////            actions -> actions.flatMap(action ->
////                    snippetsRepository.deleteOutput(action.output())
////                            .andThen(Observable.just(EditorResult.DeleteOutputResult.success()))
////                            .flatMap(snippet ->
////                                    pairWithDelay(
////                                            EditorResult.DeleteOutputResult.success(),
////                                            EditorResult.DeleteOutputResult.loading()
////                                    )
////                            )
////                            .onErrorReturn(EditorResult.DeleteOutputResult::failure)
////                            .subscribeOn(schedulerProvider.io()))
////                    .observeOn(schedulerProvider.ui())
////                    .startWith(EditorResult.DeleteOutputResult.loading());
////
////    private ObservableTransformer<EditorAction.OpenSnippets, EditorResult.OpenSnippetsResult> openSnippetProcessor =
////            actions -> action ->
////                    snippetsRepository.openSnippets()
////                            .andThen(Observable.just(EditorResult.OpenSnippetsResult.success()))
////                            .flatMap(snippet ->
////                                    pairWithDelay(
////                                            EditorResult.OpenSnippetsResult.success(),
////                                            EditorResult.OpenSnippetsResult.success()
////                                    )
////                            )
////
////                            .onErrorReturn(EditorResult.OpenSnippetsResult::failure)
////                            .subscribeOn(schedulerProvider.io())
////                            .observeOn(schedulerProvider.ui());
////
////    private ObservableTransformer<EditorAction.SearchOutputOnline, EditorResult.SearchOutputResult> searchOutputOnlineProcessor =
////            actions -> actions.flatMap(action ->
////                    snippetsRepository.searchOutputOnline(action.output())
////                            .andThen(Observable.just(EditorResult.SearchOutputResult.success()))
////
////                            .flatMap(snippet ->
////                                    pairWithDelay(
////                                            EditorResult.SearchOutputResult.success(),
////                                            EditorResult.SearchOutputResult.loading()
////                                    )
////                            )
////                            .onErrorReturn(EditorResult.SearchOutputResult::failure)
////                            .subscribeOn(schedulerProvider.io()))
////                    .observeOn(schedulerProvider.ui())
////                    .startWith(EditorResult.SearchOutputResult.loading());
////
////    private ObservableTransformer<EditorAction.ShareOnWhatsApp, EditorResult.ShareOutputWhatsAppResult> shareOnWhatsAppProcessor =
////            actions -> actions.flatMap(action ->
////                    snippetsRepository.shareOnWhatsApp(action.message())
////                            .andThen(Observable.just(EditorResult.ShareOutputWhatsAppResult.success()))
////                            .flatMap(snippet ->
////                                    pairWithDelay(
////                                            EditorResult.ShareOutputWhatsAppResult.success(),
////                                            EditorResult.ShareOutputWhatsAppResult.loading()
////                                    )
////                            )
////                            .onErrorReturn(EditorResult.ShareOutputWhatsAppResult::failure))
////                    .subscribeOn(schedulerProvider.io())
////                    .observeOn(schedulerProvider.ui())
////                    .startWith(EditorResult.ShareOutputWhatsAppResult.loading());
////
////    ObservableTransformer<EditorAction, EditorResult> actionProcessor =
////            actions -> actions.publish(
////                    shared ->
////                            Observable.merge(
////
////                                    shared.ofType(EditorAction.CompileInput.class).compose(compileInputProcessor),
////                                    shared.ofType(EditorAction.DeleteInput.class).compose(deleteInputProcessor),
////                                    shared.ofType(EditorAction.DeleteOutput.class).compose(deleteOutputProcessor),
////                                    shared.ofType(EditorAction.OpenSnippets.class).compose(openSnippetProcessor),
////                                    shared.ofType(EditorAction.SearchOutputOnline.class).compose(searchOutputOnlineProcessor),
////                                    shared.ofType(EditorAction.ShareOnWhatsApp.class).compose(shareOnWhatsAppProcessor)
////
////                                            .mergeWith(
////                                                    shared.filter(v -> !(v instanceof EditorAction.CompileInput)  &&
////                                                            !(v instanceof EditorAction.DeleteInput)  &&
////                                                            !(v instanceof EditorAction.DeleteOutput)  &&
////                                                            !(v instanceof EditorAction.OpenSnippets) &&
////                                                            !(v instanceof EditorAction.SearchOutputOnline) &&
////                                                            !(v instanceof EditorAction.ShareOnWhatsApp))
////                                                            .flatMap(w -> Observable.error(
////                                                                    new IllegalArgumentException("Unknown action type: " + w))))));