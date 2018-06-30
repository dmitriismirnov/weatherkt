package smirnov.dmitrii.weatherkt.presentation.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.MvpView
import smirnov.dmitrii.weatherkt.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Дмитрий
 * @version 13.06.2018.
 */
abstract class BaseFragment : MvpAppCompatFragment(), MvpView {

    @LayoutRes
    protected abstract fun getLayout(): Int

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        savedInstanceState?.let { restoreState(it) }
//    }
//
//    protected open fun restoreState(state: Bundle) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(getLayout(), container, false)

/*    protected fun showProgressDialog(progress: Boolean) {
        if (!isAdded) return

        val fragment = childFragmentManager.findFragmentByTag(PROGRESS_TAG)
        if (fragment != null && !progress) {
            (fragment as ProgressDialog).dismissAllowingStateLoss()
            childFragmentManager.executePendingTransactions()
        } else if (fragment == null && progress) {
            ProgressDialog().show(childFragmentManager, PROGRESS_TAG)
            childFragmentManager.executePendingTransactions()
        }
    }*/

    fun Double.toCelsiusString(): String {
        val result = Math.round(this - 273.15)
        return result.toString() + context?.getString(R.string.celsius)
    }

    fun Double.toDegreeString(): String {
        val degree = this
        return if (degree > 337 || degree < 23) {
            getString(R.string.north)
        } else if (degree > 293) {
            getString(R.string.north) + getString(R.string.west)
        } else if (degree > 248) {
            getString(R.string.west)
        } else if (degree > 203) {
            getString(R.string.south) + getString(R.string.west)
        } else if (degree > 158) {
            getString(R.string.south)
        } else if (degree > 113) {
            getString(R.string.south) + getString(R.string.east)
        } else if (degree > 68) {
            getString(R.string.east)
        } else {
            getString(R.string.north) + getString(R.string.east)
        }
    }

    fun String.toIconUrl(): String {
        return "http://openweathermap.org/img/w/" + this + ".png"
    }

    @SuppressLint("SimpleDateFormat")
    fun Long.toTimeString(): String {
        val sdf = SimpleDateFormat("HH:mm")
        val d = Date(this)
        return sdf.format(d)
    }

}