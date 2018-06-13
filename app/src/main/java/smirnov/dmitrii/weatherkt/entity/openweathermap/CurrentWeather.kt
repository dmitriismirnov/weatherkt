package smirnov.dmitrii.weatherkt.entity.openweathermap

import com.google.gson.annotations.SerializedName

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
data class CurrentWeather(
        @SerializedName("coord") val coordinate: Coordinate?,
        @SerializedName("sys") val sys: Sys?,
        @SerializedName("weather") val weatherList: List<Weather>?,
        @SerializedName("base") val base: String?,
        @SerializedName("main") val main: Main?,
        @SerializedName("wind") val wind: Wind?,
        @SerializedName("clouds") val clouds: Clouds?,
        @SerializedName("dt") val dt: Long?,
        @SerializedName("id") val id: Long?,
        @SerializedName("name") val name: String?,
        @SerializedName("cod") val cod: Int?
)