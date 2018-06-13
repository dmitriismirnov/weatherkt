package smirnov.dmitrii.weatherkt.app.system

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
class AppSchedulers : SchedulersProvider {
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun computation(): Scheduler = Schedulers.computation()
    override fun io(): Scheduler = Schedulers.io()
}