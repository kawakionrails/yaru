package io.github.kawakionrails.yaru.presenter.fragments.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import io.github.kawakionrails.yaru.databinding.FragmentSplashBinding
import io.github.kawakionrails.yaru.presenter.base.BaseFragment

@AndroidEntryPoint
@WithFragmentBindings
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater, container, false)
    }

    override fun setUpFragment() {
        setUpObservables()
    }

    private fun setUpObservables() {
        with(binding) {
            splashViewModel.goToHome(findNavController())
        }
    }

}