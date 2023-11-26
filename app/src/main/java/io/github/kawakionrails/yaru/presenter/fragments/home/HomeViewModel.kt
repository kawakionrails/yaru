package io.github.kawakionrails.yaru.presenter.fragments.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kawakionrails.yaru.data.api.RetrofitService
import io.github.kawakionrails.yaru.data.models.RandomUser
import io.github.kawakionrails.yaru.presenter.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    private val _randomUserState: MutableStateFlow<RandomUserState<RandomUser>> =
        MutableStateFlow(RandomUserState.Idle)
    val randomUserState: StateFlow<RandomUserState<RandomUser>>
        get() = _randomUserState

    sealed class RandomUserState<out T> {
        data object Idle : RandomUserState<Nothing>()
        data object Loading : RandomUserState<Nothing>()
        data class Success<out T>(val data: T) : RandomUserState<T>()
        data class Failure(val message: String) : RandomUserState<Nothing>()
    }

    fun getRandomUser() {
        viewModelScope.launch {
            _randomUserState.value = RandomUserState.Loading
            try {
                val response = RetrofitService.randomUserService.getRandomUser()
                if (response.isSuccessful) {
                    val randomUser = response.body()?.results?.get(0)
                    _randomUserState.value = if (randomUser != null) {
                        RandomUserState.Success(randomUser)
                    } else {
                        RandomUserState.Failure("Unknown data.")
                    }
                } else {
                    _randomUserState.value =
                        RandomUserState.Failure("[${response.code()}]: ${response.message()}")
                }
            } catch (exception: Exception) {
                _randomUserState.value =
                    RandomUserState.Failure(exception.message ?: "Unknown error.")
            }
        }
    }

}