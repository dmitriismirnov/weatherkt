package smirnov.dmitrii.weatherkt.presentation.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.window_point_details.view.*
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
import smirnov.dmitrii.weatherkt.extensions.toCelsiusString

/**
 * @author Дмитрий
 * @version 23.06.2018.
 */
class PointDetailsWindow(private var context: Context, private val weather: CurrentWeather) : GoogleMap.InfoWindowAdapter {

    @SuppressLint("SetTextI18n")
    override fun getInfoContents(marker: Marker?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.window_point_details, null)

        val iconUrl = "http://openweathermap.org/img/w/" + weather.weatherList?.first()?.icon + ".png"
        Picasso.with(context).load(iconUrl).into(view.iconView)

        view.locationName.text = weather.name

        view.temperature.text = weather.main?.temp?.toCelsiusString()
        return view

    }

    override fun getInfoWindow(marker: Marker?) = null

}