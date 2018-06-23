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
import smirnov.dmitrii.weatherkt.di.component.DaggerMainComponent
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
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
        DaggerMainComponent.builder()
                .appComponent((activity!!.application as App).appComponent)
                .build()
                .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        presenter.init()
    }

    override fun onResume() {
        super.onResume()
        swipe_refresh.setOnRefreshListener { onRefresh() }
    }

    override fun onPause() {
        swipe_refresh.setOnRefreshListener(null)
        super.onPause()
    }

    fun onRefresh() {
        presenter.requestWeather(currentCity)
    }

    override fun showProgress(isRefreshing: Boolean) {
        swipe_refresh.isRefreshing = isRefreshing
    }

    override fun showWeather(weather: CurrentWeather) {
        presenter.testLog(weather.toString())

        Picasso.with(context).load(weather.weatherList?.first()?.icon?.toIconUrl()).into(frag_curr_temp_icon)
        frag_curr_description.text = weather.weatherList?.first()?.description
        frag_curr_temp.text = weather.main?.temp?.toCelsiusString()
        frag_curr_temp_max.text = String.format(getString(R.string.max),
                weather.main?.tempMax?.toCelsiusString())
        frag_curr_temp_min.text = String.format(getString(R.string.min),
                weather.main?.tempMin?.toCelsiusString())
        frag_curr_wind.text = String.format(getString(R.string.wind),
                weather.wind?.deg?.toDegreeString())
        frag_curr_wind_deg.text = String.format(getString(R.string.direction),
                weather.wind?.deg.toString(), getString(R.string.directionUnit))
        frag_curr_wind_speed.text = String.format(getString(R.string.speed),
                weather.wind?.speed.toString(), getString(R.string.speedUnit))
        frag_curr_humidity.text = String.format(getString(R.string.humidity),
                weather.main?.humidity.toString(), getString(R.string.humidityUnit))
        frag_curr_pressure.text = String.format(getString(R.string.pressure),
                weather.main?.pressure.toString(), getString(R.string.pressureUnit))
        frag_curr_sunrise.text = String.format(getString(R.string.sunrise),
                weather.sys?.sunrise?.toTimeString())
        frag_curr_sunset.text = String.format(getString(R.string.sunset),
                weather.sys?.sunset?.toTimeString())

    }

}