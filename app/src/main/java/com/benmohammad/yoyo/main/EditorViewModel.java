package com.benmohammad.yoyo.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.benmohammad.yoyo.base.MviIntent;
import com.benmohammad.yoyo.base.MviViewModel;
import com.benmohammad.yoyo.data.Snippet;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.subjects.PublishSubject;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditorViewModel extends ViewModel implements MviViewModel<EditorIntent, EditorViewState> {


    @NonNull
    private PublishSubject<EditorIntent> intentPublishSubject;

    @NonNull
    private Observable<EditorViewState> statesObservable;

    @NonNull
    private CompositeDisposable disposables = new CompositeDisposable();

    @NonNull
    private EditorActionProcessorHolder actionProcessorHolder;

    public EditorViewModel(@NonNull EditorActionProcessorHolder actionProcessorHolder) {
        this.actionProcessorHolder = checkNotNull(actionProcessorHolder, "Processor cannot be null");
        intentPublishSubject = PublishSubject.create();
        statesObservable = compose();
    }


    private Observable<EditorViewState> compose() {
        return intentPublishSubject
                .compose(intentFilter)
                .map(this::actionFromIntent)
                .compose(actionProcessorHolder.actionProcessor)
                .scan(EditorViewState.idle(), reducer)
                .distinctUntilChanged()
                .replay(1)
                .autoConnect(0);
    }
    @Override
    public void processIntents(Observable<EditorIntent> intents) {
        disposables.add(intents.subscribe(intentPublishSubject::onNext));
    }

    @Override
    public Observable<EditorViewState> states() {
        return statesObservable;
    }

    private ObservableTransformer<EditorIntent, EditorIntent> intentFilter =
            intents -> intents.publish(shared ->
                    Observable.merge(shared.ofType(EditorIntent.CompileInputIntent.class).take(1),
                            shared.filter(intent -> !(intent instanceof EditorIntent.CompileInputIntent))));


    private EditorAction actionFromIntent(MviIntent intent) {
        if(intent instanceof EditorIntent.CompileInputIntent) {
            return EditorAction.CompileInput.create(((EditorIntent.CompileInputIntent) intent).input());
        }
        if(intent instanceof EditorIntent.OpenSnippetsIntent) {
            return EditorAction.OpenSnippets.create();
        }
        if(intent instanceof EditorIntent.DeleteInputIntent) {
            return EditorAction.DeleteInput.create(((EditorIntent.DeleteInputIntent) intent).input());
        }
        if (intent instanceof EditorIntent.DeleteOutputIntent) {
            return EditorAction.DeleteOutput.create(((EditorIntent.DeleteOutputIntent) intent).output());
        }
        if(intent instanceof EditorIntent.SearchOutputOnlineIntent) {
            return EditorAction.SearchOutputOnline.create(((EditorIntent.SearchOutputOnlineIntent) intent).output());
        }
        if(intent instanceof EditorIntent.ShareOnWhatsAppIntent) {
            return EditorAction.ShareOnWhatsApp.create(((EditorIntent.ShareOnWhatsAppIntent) intent).message());
        }
        throw new IllegalArgumentException("do not know how to handle this....");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }

    private static BiFunction<EditorViewState, EditorResult, EditorViewState> reducer =
            (previousState, result) -> {
        EditorViewState.Builder stateBuilder = previousState.buildWith();
        if(result instanceof EditorResult.CompileInputResult) {
            EditorResult.CompileInputResult compileInputResult = (EditorResult.CompileInputResult) result;
            switch(compileInputResult.status()) {
                case SUCCESS:
                    String output = compileInputResult.output();
                    return stateBuilder.loading(false).title(output).error(compileInputResult.error()).comment(output).code(output).build();
                case FAILURE:
                    return stateBuilder.loading(false).title("Error").error(null).comment(null).code(null).build();
                case LOADING:
                    return stateBuilder.loading(true).build();
            }
        } else if(result instanceof EditorResult.DeleteInputResult) {
            EditorResult.DeleteInputResult deleteInputResult = (EditorResult.DeleteInputResult) result;
            switch(deleteInputResult.status()) {
                case SUCCESS:
                    return stateBuilder.loading(false).error(deleteInputResult.error()).title("Real life").build();
                case FAILURE:
                    return stateBuilder.build();
                case LOADING:
                    return stateBuilder.build();
            }
        } else if(result instanceof EditorResult.DeleteOutputResult) {
            EditorResult.DeleteOutputResult deleteOutputResult = (EditorResult.DeleteOutputResult) result;
            switch(deleteOutputResult.status()) {
                case SUCCESS:
                    return stateBuilder.code(null).build();
                case FAILURE:
                    return stateBuilder.error(null).build();
                case LOADING:
                    return stateBuilder.build();
            }
        } else if (result instanceof EditorResult.SearchOutputResult) {
            return null;
        } else {
            return stateBuilder.build();
        }

        throw new IllegalArgumentException("NO No no");
    };


}


