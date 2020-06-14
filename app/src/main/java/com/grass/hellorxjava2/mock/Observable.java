package com.grass.hellorxjava2.mock;

import io.reactivex.functions.Function;

public class Observable {

    public static Observable create(ObservableOnSubscribe onSubscribe) {
        return new ObservableCreate(onSubscribe);
    }

    public Observable map(Function<String, String> mapFunction) {
        return new ObservableMap(this, mapFunction);
    }

    public void subscribe(Observer observer) {
    }
}
