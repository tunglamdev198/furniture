package com.lamnt.furniture.extensions

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Handle Single with IO Work
 */

fun <T> Single<T>.io(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

/**
 * Handle Observable with IO Work
 */

fun <T> Observable<T>.io(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

/**
 * Handle Observable with Computation Work
 */

fun <T> Observable<T>.computation(): Observable<T> {
    return this.subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
}


/**
 * Handle Flowable with IO Work
 */

fun <T> Flowable<T>.io(): Flowable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

/**
 * Handle Completable with IO Work
 */

fun Completable.io(): Completable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}