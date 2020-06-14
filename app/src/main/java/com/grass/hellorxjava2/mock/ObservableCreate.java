package com.grass.hellorxjava2.mock;

public class ObservableCreate extends Observable {
    private ObservableOnSubscribe onSubscribe;

    public ObservableCreate(ObservableOnSubscribe onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    @Override
    public void subscribe(Observer observer) {
        super.subscribe(observer);
        CreateEmitter emitter = new CreateEmitter(observer);
        this.onSubscribe.subscribe(emitter);
    }

}
