package smirnov.dmitrii.weatherkt.extensions

import android.annotation.SuppressLint
import android.view.View
import android.view.animation.AnimationUtils
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.entity.openweathermap.Wind
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Дмитрий
 * @version 30.06.2018.
 */
fun View.tap() {
    val animation = AnimationUtils.loadAnimation(this.context, R.anim.tap_animation)
    this.post { this.startAnimation(animation) }
}

fun Double.toCelsiusString(): String {
    val result = Math.round(this - 273.15)
    return result.toString() + App.appContext.getString(R.string.celsius)
}

fun Double.toDirectionString(): String = String.format(App.appContext.getString(R.string.direction),
        this.toDegreeString())

fun Double.toSpeedString(): String = String.format(App.appContext.getString(R.string.speed),
        this.toString(), App.appContext.getString(R.string.speedUnit))

fun Wind.toWindString(): String = String.format(App.appContext.getString(R.string.wind_short),
        this.deg?.toDegreeString(), this.speed.toString(), App.appContext.getString(R.string.speedUnit))

fun Double.toDegreeString(): String {
    val degree = this
    return if (degree > 337 || degree < 23) {
        App.appContext.getString(R.string.north)
    } else if (degree > 293) {
        App.appContext.getString(R.string.north) + App.appContext.getString(R.string.west)
    } else if (degree > 248) {
        App.appContext.getString(R.string.west)
    } else if (degree > 203) {
        App.appContext.getString(R.string.south) + App.appContext.getString(R.string.west)
    } else if (degree > 158) {
        App.appContext.getString(R.string.south)
    } else if (degree > 113) {
        App.appContext.getString(R.string.south) + App.appContext.getString(R.string.east)
    } else if (degree > 68) {
        App.appContext.getString(R.string.east)
    } else {
        App.appContext.getString(R.string.north) + App.appContext.getString(R.string.east)
    }
}

fun String.toIconUrl(): String {
    return "http://openweathermap.org/img/w/" + this + ".png"
}

@SuppressLint("SimpleDateFormat")
fun Long.toTimeString(): String {
    val sdf = SimpleDateFormat("HH:mm")
    val d = Date(this * 1000L)
    return sdf.format(d)
}

@SuppressLint("SimpleDateFormat")
fun Long.toDateString(): String {
    val sdf = SimpleDateFormat("dd MMM")
    val d = Date(this * 1000L)
    return sdf.format(d)
}