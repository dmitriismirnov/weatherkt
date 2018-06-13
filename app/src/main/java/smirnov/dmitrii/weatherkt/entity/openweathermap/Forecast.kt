package smirnov.dmitrii.weatherkt.entity.openweathermap

import com.google.gson.annotations.SerializedName

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
data class Forecast(
        @SerializedName("dt") val dt: Long?,
        @SerializedName("main") val main: Main?,
        @SerializedName("weather") val weatherList: List<Weather>?,
        @SerializedName("clouds") val clouds: Clouds?,
        @SerializedName("wind") val wind: Wind?,
        @SerializedName("sys") val sys: Sys?,
        @SerializedName("dt_txt") val dtTxt: String?,
        @SerializedName("pressure") val pressure: Double?,
        @SerializedName("humidity") val humidity: Int?,
        @SerializedName("temp") val temp: Temp?
)