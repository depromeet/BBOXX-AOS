package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.EmotionStatusEntity
import com.depromeet.bboxx.domain.model.EmotionStatus
import javax.inject.Inject

class EmotionStatusEntityMapper @Inject constructor() {
    fun trans(target: EmotionStatusEntity) : EmotionStatus = with(target){
        return EmotionStatus(emotionUrl, id, status)
    }

    fun transList(target: List<EmotionStatusEntity>) :  List<EmotionStatus> = with(target){
        return map{
            EmotionStatus(it.emotionUrl, it.id, it.status)
        }
    }
}