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
}