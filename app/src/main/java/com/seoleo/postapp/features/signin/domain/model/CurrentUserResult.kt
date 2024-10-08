package com.seoleo.postapp.features.signin.domain.model

import android.net.Uri

sealed class CurrentUserResult {

    data object CurrentUserNotAuthorized : CurrentUserResult()
    data class CurrentUserData(
        val uuid: String,
        val email: String,
        val phoneNumber: String,
        val name: String,
        val photoUrl: Uri,
    ) : CurrentUserResult()
}