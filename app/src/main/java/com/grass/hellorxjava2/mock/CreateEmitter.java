package com.grass.hellorxjava2.mock;

public class CreateEmitter {
    public Observer mObserver;

    public CreateEmitter(Observer mObserver) {
        this.mObserver = mObserver;
    }

    public void onNext(String str) {
        mObserver.onNext(str);
    }

    public void onError(Throwable e) {
        mObserver.onError(e);
    }

    public void onComplete() {
        mObserver.onComplete();
    }}
