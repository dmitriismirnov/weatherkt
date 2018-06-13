package smirnov.dmitrii.weatherkt.entity.openweathermap

import com.google.gson.annotations.SerializedName

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
data class Coordinate(
        @SerializedName("lat") val latitude:Double?,
        @SerializedName("lon") val longitude:Double?
)