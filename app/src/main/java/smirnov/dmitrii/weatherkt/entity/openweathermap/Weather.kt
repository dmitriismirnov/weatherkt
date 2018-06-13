package smirnov.dmitrii.weatherkt.entity.openweathermap

import com.google.gson.annotations.SerializedName

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
data class Weather(
        @SerializedName("id") val id: Long?,
        @SerializedName("main") val main: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("icon") val icon: String?
)