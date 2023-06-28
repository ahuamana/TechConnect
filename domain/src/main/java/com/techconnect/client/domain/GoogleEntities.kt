package com.techconnect.client.domain

data class SignInResult(
    val data: UserGoogle?,
    val errorMessage:String?
)

data class UserGoogle(
    val email:String,
    val username:String?,
    val profilePictureUrl:String?,
) {
    constructor():this(
        email = "",
        username = "",
        profilePictureUrl = "")
}

