package io.github.kawakionrails.yaru.presenter.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import io.github.kawakionrails.yaru.databinding.FragmentHomeBinding
import io.github.kawakionrails.yaru.presenter.base.BaseFragment
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }

    override fun setUpFragment() {
        setUpObservables()
    }

    private fun setUpObservables() {
        with(binding) {
            generate.setOnClickListener {
                homeViewModel.getRandomUser()
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    homeViewModel.randomUserState.collect { result ->
                        when (result) {
                            is HomeViewModel.RandomUserState.Idle -> {
                                // TODO: Not yet implemented
                            }

                            is HomeViewModel.RandomUserState.Loading -> {
                                // TODO: Not yet implemented
                            }

                            is HomeViewModel.RandomUserState.Success -> {
                                val randomUser = result.data
                                gender.setText(randomUser.gender)
                                firstName.setText(randomUser.name.first)
                                lastName.setText(randomUser.name.last)
                                Snackbar.make(
                                    homeLayout,
                                    "User generated successfully!",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                            is HomeViewModel.RandomUserState.Failure -> {
                                Snackbar.make(
                                    homeLayout,
                                    result.message,
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

}