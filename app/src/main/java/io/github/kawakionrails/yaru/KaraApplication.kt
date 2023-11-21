package io.github.kawakionrails.yaru

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KaraApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}