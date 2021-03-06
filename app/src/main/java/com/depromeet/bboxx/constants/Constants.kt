package com.depromeet.bboxx.constants

object Constants {
    const val C_SUCCESS = "SUCCESS"
    const val C_FAILED = "FAILED"

    const val C_MOVE_TEAM = "Team"
    const val C_MOVE_TERMS_OF_USE = "TermsOfUse"
    const val C_MOVE_PERSON_INFO = "PersonInfo"
    //const val BASE_URL = "https://7006-183-98-56-105.ngrok.io/api/v1/"
    //const val BASE_URL = "https://713c-221-148-138-188.ngrok.io/api/v1/"
    const val BASE_URL = "http://ecs-loadbalancer-640d958083694585.elb.ap-northeast-2.amazonaws.com/api/v1/"

    val REMOTE_CONFIG_CACHE_EXPIRATON = 1000
}