package io.github.kawakionrails.yaru.presenter.activities.main

import dagger.hilt.android.AndroidEntryPoint
import io.github.kawakionrails.yaru.databinding.ActivityMainBinding
import io.github.kawakionrails.yaru.presenter.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setUpActivity() {
        setUpObservables()
    }

    private fun setUpObservables() {
        // TODO: Not yet implemented
    }

}