package com.grass.hellorxjava2.mock;

public interface Observer {
    void onNext(String str);
    void onError(Throwable e);
    void onComplete();
}
