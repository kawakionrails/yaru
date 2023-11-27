package io.github.kawakionrails.yaru.presenter.fragments.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import io.github.kawakionrails.yaru.R
import io.github.kawakionrails.yaru.databinding.FragmentHomeBinding
import io.github.kawakionrails.yaru.presenter.base.BaseFragment
import kotlinx.coroutines.launch

@AndroidEntryPoint
@WithFragmentBindings
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
            val genderAdapter =
                ArrayAdapter(requireContext(), R.layout.item_gender, homeViewModel.genderItems)
            gender.setAdapter(genderAdapter)
            gender.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // TODO: Not yet implemented
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // TODO: Not yet implemented
                }

                override fun afterTextChanged(s: Editable?) {
                    // TODO: Not yet implemented
                }
            })
            generate.setOnClickListener {
                val gender =
                    if (gender.text.toString() == "Random") ""
                    else gender.text.toString().lowercase()
                homeViewModel.getRandomUser(gender)
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    homeViewModel.randomUserState.collect { result ->
                        when (result) {
                            is HomeViewModel.RandomUserState.Idle -> {
                                generate.isEnabled = true
                            }

                            is HomeViewModel.RandomUserState.Loading -> {
                                generate.isEnabled = false
                            }

                            is HomeViewModel.RandomUserState.Success -> {
                                generate.isEnabled = true
                                val randomUser = result.data
                                firstName.setText(randomUser.name.first)
                                lastName.setText(randomUser.name.last)
                                dateOfBirth.setText(randomUser.dob.date)
                                email.setText(randomUser.email)
                                Snackbar.make(
                                    homeLayout,
                                    "User generated successfully!",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                            is HomeViewModel.RandomUserState.Failure -> {
                                generate.isEnabled = true
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