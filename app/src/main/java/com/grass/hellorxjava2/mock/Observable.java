package com.grass.hellorxjava2.mock;

public class Observable {

    public static Observable create(ObservableOnSubscribe onSubscribe) {
        return new ObservableCreate(onSubscribe);
    }

    public void subscribe(Observer observer) {
    }
}
