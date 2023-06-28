package com.techconnect.client.domain

data class UserReponse (
    val email: String,
    val username: String,
    val profilePictureUrl: String,
    val isAdministrator: Boolean,
    val isModerator: Boolean,
    val isUser: Boolean,
){
    constructor():this(
        email = "",
        username = "",
        profilePictureUrl = "",
        isAdministrator = false,
        isModerator = false,
        isUser = false,
    )
}

data class UserDomain(
    val email: String,
    val username: String,
    val profilePictureUrl: String,
    val isAdministrator: Boolean,
    val isModerator: Boolean,
    val isUser: Boolean,
)

fun UserReponse.toUserDomain() = UserDomain(
    email = email,
    username = username,
    profilePictureUrl = profilePictureUrl,
    isAdministrator = isAdministrator,
    isModerator = isModerator,
    isUser = isUser,
)

fun UserDomain.toUserDbFromDomain() = UserReponse(
    email = email,
    username = username,
    profilePictureUrl = profilePictureUrl,
    isAdministrator = isAdministrator,
    isModerator = isModerator,
    isUser = isUser,
)

fun UserReponse.toUserData() = UserGoogle(
    email = email,
    username = username,
    profilePictureUrl = profilePictureUrl,
)

fun UserGoogle.toUserDomain() = UserDomain(
    email = email,
    username = username?: "",
    profilePictureUrl = profilePictureUrl?: "",
    isAdministrator = false,
    isModerator = false,
    isUser = true,
)