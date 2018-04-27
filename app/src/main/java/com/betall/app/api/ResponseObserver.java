package com.betall.app.api;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by fly on 2018/1/27.
 */

public class ResponseObserver<T> implements SingleObserver<T> {

    public interface OnData<T> {
        void call(T data);
    }

    public interface OnError {
        void call(Throwable e);
    }

    private Disposable disposable;
    private OnData<T> onData;
    private OnError onError;

    public ResponseObserver(OnData<T> onData, OnError onError) {
        this.onData = onData;
        this.onError = onError;
    }

    public ResponseObserver(OnData<T> onData) {
        this.onData = onData;
    }

    public void cancel() {
        if (!this.disposable.isDisposed())
            this.disposable.dispose();
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
    }

    @Override
    public void onSuccess(T data) {
        if (this.onData != null)
            this.onData.call(data);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (this.onError != null)
            this.onError.call(e);
    }
}
