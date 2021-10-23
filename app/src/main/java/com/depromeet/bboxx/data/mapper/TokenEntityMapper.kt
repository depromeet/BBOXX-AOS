package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.TokenEntity
import com.depromeet.bboxx.domain.model.Token
import javax.inject.Inject

class TokenEntityMapper @Inject constructor() {
    fun trans(target: TokenEntity): Token = with(target) {
        return Token(token)
    }
}