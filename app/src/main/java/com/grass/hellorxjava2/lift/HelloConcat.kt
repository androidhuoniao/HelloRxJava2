package com.grass.hellorxjava2.lift

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.NullPointerException
import java.util.*

/**
 * concat 操作符的测试用例
 */
class HelloConcat {
    private val TAG = "HelloConcat"

    fun test() {
//        test1()
        test2()
//        test3()
//        test4()
    }

    fun test1() {
        Observable.concat(loadDefault(), loadFromNet()).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    Log.i(TAG, "test1 onSubscribe")

                }

                override fun onNext(t: String) {
                    Log.i(TAG, "test1 onNext $t")
                }

                override fun onError(e: Throwable) {
                    Log.i(TAG, "test1 onError $e")

                }
            })

    }

    fun test2() {
        var listOf = listOf<Observable<String>>(loadDefaultError(), loadFromNet())
//        Observable.concat(loadDefaultError(), loadFromNet()).subscribeOn(Schedulers.io())
        Observable.concatDelayError(listOf)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    Log.i(TAG, "test2 onSubscribe")

                }

                override fun onNext(t: String) {
                    Log.i(TAG, "test2 onNext $t")
                }

                override fun onError(e: Throwable) {
                    Log.i(TAG, "test2 onError $e")

                }
            })

    }

    fun test3() {
        var listOf = listOf<Observable<String>>(loadDefault(), loadFromNetError())
//        Observable.concat(loadDefault(), loadFromNetError()).subscribeOn(Schedulers.io())
        Observable.concatDelayError(listOf).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    Log.i(TAG, "test3 onSubscribe")

                }

                override fun onNext(t: String) {
                    Log.i(TAG, "test3 onNext $t")
                }

                override fun onError(e: Throwable) {
                    Log.i(TAG, "test3 onError $e")

                }
            })

    }

    fun test4() {
        var listOf = listOf<Observable<String>>(loadDefaultError(), loadFromNetError())
//        Observable.concat(loadDefaultError(), loadFromNetError()).subscribeOn(Schedulers.io())
        Observable.concatDelayError(listOf)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    Log.i(TAG, "test4 onSubscribe")

                }

                override fun onNext(t: String) {
                    Log.i(TAG, "test4 onNext $t")
                }

                override fun onError(e: Throwable) {
                    Log.i(TAG, "test4 onError $e")

                }
            })

    }

    fun loadDefault(): Observable<String> {
        return Observable.create {
            it.onNext("i am default")
            it.onComplete()
        }
    }

    fun loadFromNet(): Observable<String> {
        return Observable.create {
            it.onNext("i am from net")
            it.onComplete()
        }
    }


    fun loadDefaultError(): Observable<String> {
        return Observable.create {
            if (true) {
                throw NullPointerException("loadDefaultError")
            }
            it.onNext("i am default error")
        }
    }

    fun loadFromNetError(): Observable<String> {
        return Observable.create {
            if (true) {
                throw NullPointerException("loadFromNetError")
            }
            it.onNext("i am from net error")
        }
    }
}