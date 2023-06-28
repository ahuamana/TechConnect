package com.techconnect.client.modules.login.presentation

data class SignInState(
    val isSignInSuccessful:Boolean = false,
    val signInError:String? = null
)
