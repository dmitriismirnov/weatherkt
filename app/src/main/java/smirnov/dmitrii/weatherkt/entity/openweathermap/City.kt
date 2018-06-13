package smirnov.dmitrii.weatherkt.entity.openweathermap

import com.google.gson.annotations.SerializedName

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
data class City(
        @SerializedName("id") val id: Long?,
        @SerializedName("name") val name: String?
)