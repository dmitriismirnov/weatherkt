package smirnov.dmitrii.weatherkt.presentation.screens.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detailed_weather.*
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
import smirnov.dmitrii.weatherkt.extensions.toCelsiusString
import smirnov.dmitrii.weatherkt.extensions.toDegreeString
import smirnov.dmitrii.weatherkt.extensions.toIconUrl
import smirnov.dmitrii.weatherkt.extensions.toTimeString
import smirnov.dmitrii.weatherkt.presentation.base.BaseFragment
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 16.06.2018.
 */
class DetailsFragment : BaseFragment(), DetailsView {
    override fun showError(it: Throwable) {
        Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    override fun getLayout() = R.layout.fragment_detailed_weather

    @Inject
    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private var currentCity = "Saint Petersburg"

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        swipe_refresh.setOnRefreshListener { onRefresh() }
        presenter.requestWeather()
    }

    override fun onPause() {
        swipe_refresh.setOnRefreshListener(null)
        super.onPause()
    }

    fun onRefresh() {
        presenter.requestWeather()
    }

    override fun showProgress(isRefreshing: Boolean) {
        swipe_refresh.isRefreshing = isRefreshing
    }

    override fun showWeather(weather: CurrentWeather) {
        presenter.testLog(weather.toString())

        Picasso.with(context).load(weather.weatherList?.first()?.icon?.toIconUrl()).into(weather_icon)
        city.text = weather.name
        description.text = weather.weatherList?.first()?.description
        temperature.text = weather.main?.temp?.toCelsiusString()
        temperature_max.text = String.format(getString(R.string.max),
                weather.main?.tempMax?.toCelsiusString())
        temperature_min.text = String.format(getString(R.string.min),
                weather.main?.tempMin?.toCelsiusString())
        wind.text = String.format(getString(R.string.wind),
                weather.wind?.deg?.toDegreeString())
        wind_deg.text = String.format(getString(R.string.direction),
                weather.wind?.deg.toString(), getString(R.string.directionUnit))
        wind_speed.text = String.format(getString(R.string.speed),
                weather.wind?.speed.toString(), getString(R.string.speedUnit))
        humidity.text = String.format(getString(R.string.humidity),
                weather.main?.humidity.toString(), getString(R.string.humidityUnit))
        pressure.text = String.format(getString(R.string.pressure),
                weather.main?.pressure.toString(), getString(R.string.pressureUnit))
        sunrise.text = String.format(getString(R.string.sunrise),
                weather.sys?.sunrise?.toTimeString())
        sunset.text = String.format(getString(R.string.sunset),
                weather.sys?.sunset?.toTimeString())

    }

}