package io.github.steelahhh.core

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProviderImpl : SchedulerProvider {
    override fun main(): Scheduler = Schedulers.io()
    override fun computation(): Scheduler = Schedulers.computation()
    override fun io(): Scheduler = AndroidSchedulers.mainThread()
}
