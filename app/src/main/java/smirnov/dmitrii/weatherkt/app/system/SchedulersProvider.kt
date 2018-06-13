package smirnov.dmitrii.weatherkt.app.system

import io.reactivex.Scheduler

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
interface SchedulersProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}