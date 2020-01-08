package com.mylektop.ui.component.splash

import android.os.Bundle
import android.os.Handler
import com.mylektop.R
import com.mylektop.ui.ViewModelFactory
import com.mylektop.ui.base.BaseActivity
import com.mylektop.ui.component.news.NewsListActivity
import com.mylektop.utils.Constants
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class SplashActivity : BaseActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override val layoutId: Int
        get() = R.layout.splash_layout

    override fun initializeViewModel() {
        splashViewModel = viewModelFactory.create(splashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            startActivity<NewsListActivity>()
            finish()
        }, Constants.SPLASH_DELAY.toLong())
    }
}