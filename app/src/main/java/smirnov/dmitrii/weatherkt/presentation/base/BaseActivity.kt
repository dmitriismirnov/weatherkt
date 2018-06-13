package smirnov.dmitrii.weatherkt.presentation.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
abstract class BaseActivity<P : BasePresenter<Any>> : AppCompatActivity() {
    @Inject
    lateinit var presenter: P

    @LayoutRes
    protected abstract fun getLayout(): Int
    protected abstract fun initInjector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initInjector()
        initPresenter()
    }

    private fun initPresenter() {
        presenter.attachView(this)
        presenter.initialise()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}