package com.depromeet.bboxx.domain.enums

enum class PlatformType(val type : Int){
    //MOBILE, EMAIL은 서버에서 받아오는 값이 아니고 센트비 앱 로그인 시 로그인 화면에서 분기처리 하기 위해 선
    KAKAO(1), GOOGLE(2), APPLE(3),EMAIL(4), UNKNOWN(-1);
    companion object{

        fun findPlatform(type: Int): PlatformType{
            return PlatformType.values().firstOrNull { it.type == type } ?: UNKNOWN
        }

    }
}
