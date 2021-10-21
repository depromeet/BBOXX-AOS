package com.depromeet.bboxx.domain.enums

enum class ProviderType(val type: String) {
    KAKAO("kakao"), GOOGLE("google"), UNKNOWN("");

    companion object {

        fun findProvider(type: String): ProviderType {
            return ProviderType.values().firstOrNull { it.type == type } ?: UNKNOWN
        }

    }
}