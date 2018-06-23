package smirnov.dmitrii.weatherkt.presentation.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.MvpView

/**
 * @author Дмитрий
 * @version 13.06.2018.
 */
abstract class BaseFragment : MvpAppCompatFragment(), MvpView {

    @LayoutRes
    protected abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(getLayout(), container, false)

    fun Double.kelvinToCelsius(kelvin : String) : String {
        val inKelvin: Double
        try {
            inKelvin = java.lang.Double.parseDouble(kelvin)
        } catch (e: NumberFormatException) {
            throw e
        }

        val result = Math.round(inKelvin - 273.15)
        return result.toString()
    }

}