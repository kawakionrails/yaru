package io.github.kawakionrails.yaru.presenter.fragments.splash

import android.os.Handler
import android.os.Looper
import androidx.navigation.NavController
import io.github.kawakionrails.yaru.R
import io.github.kawakionrails.yaru.presenter.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    fun goToHome(navController: NavController) {
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate(R.id.action_splashFragment_to_homeFragment)
        }, 3000)
    }

}