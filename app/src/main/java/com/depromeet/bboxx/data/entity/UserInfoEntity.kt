package com.depromeet.bboxx.data.entity


import com.google.gson.annotations.SerializedName

data class UserInfoEntity(
    @SerializedName("id")
    val id : Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("socialProviderType")
    val socialProviderType: String,
    @SerializedName("state")
    val state: String
)