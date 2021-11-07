package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.TokenValidEntity
import com.depromeet.bboxx.domain.model.TokenValid
import javax.inject.Inject

class TokenValidEntityMapper @Inject constructor() {
    fun trans(target: TokenValidEntity): TokenValid = with(target) {
        return TokenValid(valid)
    }
}