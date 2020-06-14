package com.grass.hellorxjava2.mock;

import io.reactivex.functions.Function;

public class ObservableMap extends AbstractObservableWithUpstream {

    private Function<String, String> mappingFunction;

    public ObservableMap(Observable observable, Function<String, String> mapFunction) {
        super(observable);
        this.mappingFunction = mapFunction;
    }

    @Override
    public void subscribe(Observer observer) {
        source().subscribe(new MapObserver(observer));
    }

    class MapObserver implements Observer {
        public Observer innerObserver;

        public MapObserver(Observer innerObserver) {
            this.innerObserver = innerObserver;
        }

        @Override
        public void onNext(String str) {
            try {
                String apply = mappingFunction.apply(str);
                innerObserver.onNext(apply);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {
            innerObserver.onError(e);
        }

        @Override
        public void onComplete() {
            innerObserver.onComplete();
        }
    }


}
