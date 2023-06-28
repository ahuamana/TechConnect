package com.techconnect.client.modules.dashboard.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahuaman.client.usecases.GetUserSignedInfoUseCase
import com.techconnect.client.domain.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getUserSignedInfoUseCase: GetUserSignedInfoUseCase
):ViewModel(

){

    private val _userData = mutableStateOf<UserData?>(UserData())
    val user: State<UserData?> = _userData

    init {
        getSignedInUser()
    }


    fun getSignedInUser() = viewModelScope.launch {
        getUserSignedInfoUseCase
            .invoke()
            .onStart {
                // emit loading state
            }.onEach {
                // emit data state
                _userData.value = it
            }.catch {
                // emit error state
            }.launchIn(viewModelScope)

    }

}