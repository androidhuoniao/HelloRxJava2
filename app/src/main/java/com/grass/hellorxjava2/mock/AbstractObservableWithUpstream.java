package com.grass.hellorxjava2.mock;

public class AbstractObservableWithUpstream extends Observable{
    public Observable mInnerObservable;

    public AbstractObservableWithUpstream(Observable mInnerObservable) {
        this.mInnerObservable = mInnerObservable;
    }

    public Observable source() {
        return mInnerObservable;
    }

}
