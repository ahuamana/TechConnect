package com.techconnect.client.modules.login.presentation

import android.content.Intent
import android.content.IntentSender
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahuaman.client.usecases.SignInUsecase
import com.ahuaman.client.usecases.SignInWithIntentUsecase
import com.techconnect.client.domain.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val signInWithIntentUsecase: SignInWithIntentUsecase,
    private val signInUsecase: SignInUsecase
): ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private val _signInIntentSender = MutableStateFlow<IntentSender?>(null)
    val signInIntentSender = _signInIntentSender.asStateFlow()


    private val mutableStateOf = mutableStateOf(mutableStateOf(false))
    val stateOf = mutableStateOf


    private fun onSignInResult(result: SignInResult){
       _state.update {it.copy(
              isSignInSuccessful = result.data != null,
              signInError = result.errorMessage
       ) }
    }

    fun resetState(){
        _state.update { SignInState() }
    }

    fun signIn(intent: Intent) = viewModelScope.launch{
        signInWithIntentUsecase
            .invoke(intent = intent)
            .onStart {
                // emit loading state
            }.onEach {
                onSignInResult(it)
            }.catch {
                // emit error state
            }.launchIn(viewModelScope)
    }

    fun signInIntentSender() = viewModelScope.launch{
        signInUsecase
                .invoke()
                .onStart {
                    // emit loading state
                }.onEach {
                    _signInIntentSender.value = it
                }.catch {
                    // emit error state
                }.launchIn(viewModelScope)
    }
}