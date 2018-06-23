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
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather

/**
 * @author Дмитрий
 * @version 23.06.2018.
 */
class PointDetailsWindow(private var context: Context, private var weather: CurrentWeather) : GoogleMap.InfoWindowAdapter {

    @SuppressLint("SetTextI18n")
    override fun getInfoContents(marker: Marker?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.window_point_details, null)
        view.locationName.text = weather.name

        val temp = kelvinToCelsius(weather.main?.temp.toString())

        view.temperature.text = "$temp °C"

        val iconUrl = "http://openweathermap.org/img/w/" + weather.weatherList?.first()?.icon + ".png"
        Log.d("PointDetailsWindow", iconUrl)
        Picasso.with(context).load(iconUrl).into(view.iconView)
        return view

    }

    @Throws(NumberFormatException::class)
    fun kelvinToCelsius(kelvin: String): String {
        val inKelvin: Double
        try {
            inKelvin = java.lang.Double.parseDouble(kelvin)
        } catch (e: NumberFormatException) {
            throw e
        }

        val result = Math.round(inKelvin - 273.15).toInt()
        return result.toString()
    }

    override fun getInfoWindow(marker: Marker?) = null

}