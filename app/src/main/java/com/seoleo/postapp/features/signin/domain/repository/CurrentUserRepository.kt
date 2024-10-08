package com.seoleo.postapp.features.signin.domain.repository

import com.seoleo.postapp.features.signin.domain.model.CurrentUserResult

interface CurrentUserRepository {

    fun getCurrentUser(): CurrentUserResult
}