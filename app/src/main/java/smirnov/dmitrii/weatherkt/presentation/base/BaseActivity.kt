package smirnov.dmitrii.weatherkt.presentation.base

import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpView
import smirnov.dmitrii.weatherkt.R
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
abstract class BaseActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    @LayoutRes
    abstract fun getLayout(): Int

    protected fun switchFragment(fragment: BaseFragment) {
/*
        fragmentManager = supportFragmentManager

        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(R.id.container, fragment)
        transaction.commit()
        */
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commitAllowingStateLoss()
    }

    fun showMessageSnackbar(message: Int) {
        val viewGroup = (this.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        Snackbar.make(viewGroup, message, Snackbar.LENGTH_LONG).show()
    }

    fun showMessage(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showMessageDialog(message: Int, listener: DialogInterface.OnClickListener?) {
        AlertDialog.Builder(this)
                .setCancelable(listener == null)
                .setPositiveButton(getString(android.R.string.ok), listener)
                .setMessage(message)
                .show()
    }
}