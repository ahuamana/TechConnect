package com.techconnect.client.domain

data class SignInResult(
    val data: UserData?,
    val errorMessage:String?
)

data class UserData(
    val email:String,
    val username:String?,
    val profilePictureUrl:String?,
) {
    constructor():this(
        email = "",
        username = "",
        profilePictureUrl = "")
}

