package com.grass.hellorxjava2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.start).setOnClickListener {
            test()
            test2()
        }
    }

    override fun onResume() {
        super.onResume()
        test()
    }

    fun test() {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                if (!emitter.isDisposed) {
                }
                Log.i(TAG, "test is working1 ${emitter.isDisposed}")
                emitter.onNext(1)
                Log.i(TAG, "test is working2 ${emitter.isDisposed}")
                emitter.onNext(2)
            }
        })
            .doOnSubscribe(checkAccount())
            .subscribeOn(Schedulers.io())
            .compose(uploadLogError(TAG, "同步关注列表出错"))
            .publish()
            .autoConnect()
            .onErrorResumeNext(Observable.empty())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.i(TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.i(TAG, "onSubscribe: ")
                }

                override fun onNext(t: Int) {
                    Log.i(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                    Log.i(TAG, "onError: $e")
                }
            })


    }

    fun checkAccount(): Consumer<in Disposable?>? {
        return Consumer {
            throw NullPointerException("account exception")
        }
    }

    /**
     * 记录错误信息到线上日志.
     */
    fun <T> uploadLogError(tag: String?, msg: String): ObservableTransformer<T, T>? {
        return ObservableTransformer { source ->
            source.doOnError { throwable ->
                Log.i(
                    TAG,
                    msg + ": " + throwable.message.toString()
                )
            }
        }
    }

    fun test2() {
        Observable.create<Int> { emitter: ObservableEmitter<Int> ->
            emitter.onNext(1)
        }.map {
            10 * it
        }.subscribe {
            Log.i(TAG, "test2: $it")
        }
    }
}