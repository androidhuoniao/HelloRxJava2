package com.grass.hellorxjava2.mock;

import android.util.Log;

public class MockRxjava {
    private static final String TAG = "MockRxjava";

    public void test() {
        ObservableCreate.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(CreateEmitter emitter) {
                emitter.onNext("grass");
            }
        }).subscribe(new Observer() {
            @Override
            public void onNext(String str) {
                Log.i(TAG, "onNext: " + str);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });

    }
}
