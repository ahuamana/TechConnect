package com.techconnect.client.modules.dashboard.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahuaman.client.usecases.GetUserSignedInfoUseCase
import com.ahuaman.client.usecases.SearchUserByEmailUseCase
import com.ahuaman.client.usecases.SearhUserStates
import com.ahuaman.client.usecases.SetUserFirebaseUseCase
import com.techconnect.client.domain.UserDomain
import com.techconnect.client.domain.UserGoogle
import com.techconnect.client.domain.toUserDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getUserSignedFromGoogle: GetUserSignedInfoUseCase,
    private val setUserFirebaseUseCase: SetUserFirebaseUseCase,
    private val searchUserByEmailUseCase: SearchUserByEmailUseCase
):ViewModel(){

    private val _userGoogle = mutableStateOf<UserGoogle?>(UserGoogle())
    val user: State<UserGoogle?> = _userGoogle

    private val _searchUserStates = mutableStateOf<SearhUserStates>(SearhUserStates())
    val searchUserStates: State<SearhUserStates> = _searchUserStates

    init {
        signedIn()
    }


    fun signedIn() = viewModelScope.launch {
        getUserSignedFromGoogle
            .invoke()
            .onStart {
                _searchUserStates.value = SearhUserStates(
                    loading = true
                )
            }.onEach {
                // if sucess -> get info only to works with firebase
                searchUserInFirebase(it)
            }.catch {
                // emit error state
                _searchUserStates.value = SearhUserStates(
                    error = true,
                    errorMessage = it.message?: "Error"
                )
            }.launchIn(viewModelScope)

    }

    private fun handledUser(it: UserDomain, userGoogle: UserGoogle) = viewModelScope.launch {
        if(it == null){
            // if user not exist in firebase -> create user
            _searchUserStates.value = SearhUserStates(
                userNotExist = true
            )
            // register user in firebase
            registerUserOnFirebase(userGoogle.toUserDomain())
        }else{
            // if user exist in firebase -> show info
            _searchUserStates.value = SearhUserStates(
                userExist = true,
                user = it
            )
        }
    }

    private fun registerUserOnFirebase(userDomain:UserDomain) = viewModelScope.launch{
        setUserFirebaseUseCase
            .invoke(userDomain)
            .onEach {
                Timber.d("User saved in firebase")
            }.catch {

            }.launchIn(viewModelScope)
    }



    fun searchUserInFirebase(userGoogle: UserGoogle?) = viewModelScope.launch {
        userGoogle?.let {
            searchUserByEmailUseCase.invoke(it.email)
                .onStart {
                    // emit loading state
                    // Not necessary
                }.onEach {
                    // if sucess -> get info only to works with firebase
                    handledUser(it, userGoogle)

                }.catch {
                    // emit error state
                    _searchUserStates.value = SearhUserStates(
                       loading = false,
                        error = true,
                        errorMessage = it.message?: "Error"
                    )
                }.launchIn(viewModelScope)
        }
    }

}