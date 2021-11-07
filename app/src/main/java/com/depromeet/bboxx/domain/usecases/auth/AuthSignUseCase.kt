package com.depromeet.bboxx.domain.usecases.auth

import com.depromeet.bboxx.domain.repository.auth.AuthSignRepository
import javax.inject.Inject

class AuthSignUseCase @Inject constructor(
    private val authSignRepository: AuthSignRepository
) {
    fun signIn(authData: String, providerType: String) =
        authSignRepository.signIn(authData, providerType)

    fun signUp(authData: String, nickName: String, providerType: String) =
        authSignRepository.signUp(authData, nickName, providerType)

    fun validToken(token: String) =
        authSignRepository.validToken(token)
}