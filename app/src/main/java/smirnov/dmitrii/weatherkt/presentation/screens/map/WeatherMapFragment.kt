package smirnov.dmitrii.weatherkt.presentation.screens.map

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_map.*
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.di.component.DaggerMainComponent
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
import smirnov.dmitrii.weatherkt.presentation.base.BaseFragment
import smirnov.dmitrii.weatherkt.presentation.common.dialogs.ProgressDialogFragment
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 23.06.2018.
 */
class WeatherMapFragment : BaseFragment(), WeatherMapView {

    override fun getLayout() = R.layout.fragment_map

    @Inject
    @InjectPresenter
    lateinit var presenter: WeatherMapPresenter

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
        testData.setOnClickListener {
            presenter.onPointClicked(20.32, 59.94)
        }
    }

    override fun showProgress(isRefreshing: Boolean) {
        if (isRefreshing)
            ProgressDialogFragment.showDialog(activity!!.supportFragmentManager, "loading")
        else {
            ProgressDialogFragment.dismissIfExist(activity!!.supportFragmentManager)
        }
    }

    override fun showWeather(currentWeather: CurrentWeather) {
        testData.text = currentWeather.toString()
    }

    override fun showError(error: Throwable) {
    }

}