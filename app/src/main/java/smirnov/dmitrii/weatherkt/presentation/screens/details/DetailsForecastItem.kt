package smirnov.dmitrii.weatherkt.presentation.screens.details


/**
 * @author Дмитрий
 * @version 01.07.2018.
 */
class DetailsForecastItem  {

    var type: Int = TYPE_WEATHER

    var timestamp: Long? = null

    var temperature: String? = null
    var icon: String? = null
    var wind: String? = null

    companion object {
        const val TYPE_WEATHER = 0
        const val TYPE_SUNRISE = 1
        const val TYPE_SUNSET = 2

        fun makeWeatherItem(
                timestamp: Long?,
                temperature: String?,
                icon: String?,
                wind : String?
        ): DetailsForecastItem {
            val result = DetailsForecastItem()
            result.type = TYPE_WEATHER
            result.timestamp = timestamp
            result.temperature = temperature
            result.icon = icon
            result.wind = wind
            return result
        }

        fun makeSunItem(
                timestamp: Long?,
                isSunRise: Boolean
        ): DetailsForecastItem {
            val result = DetailsForecastItem()
            result.timestamp = timestamp
            result.type = when (isSunRise) {
                true -> TYPE_SUNRISE
                false -> TYPE_SUNSET
            }
            return result
        }
    }
}