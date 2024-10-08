package com.seoleo.postapp.features.signin.domain.usecase

import com.seoleo.postapp.features.signin.domain.model.CurrentUserResult
import com.seoleo.postapp.features.signin.domain.repository.CurrentUserRepository

interface GetCurrentUserUseCase {
    fun getUser() : CurrentUserResult
}

class GetCurrentUserUseCaseImpl(
    private val currentUserRepository : CurrentUserRepository
) : GetCurrentUserUseCase {

    override fun getUser(): CurrentUserResult {
        return currentUserRepository.getCurrentUser()
    }
}