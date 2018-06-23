package smirnov.dmitrii.weatherkt.presentation.screens.details

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
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
class DetailsFragment : BaseFragment(), DetailsView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainComponent.builder()
                .appComponent((activity!!.application as App).appComponent)
                .build()
                .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.testLog("onViewCreated")
    }
    override fun onRefresh() {
        presenter.testLog("onRefresh")
    }

    override fun showWeather(weather: CurrentWeather) {
        presenter.testLog("show weather")
        presenter.testLog(weather.toString())
    }

    override fun getLayout() = R.layout.fragment_detailed_weather
}