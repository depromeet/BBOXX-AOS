package com.depromeet.bboxx.presentation.mapper

import com.depromeet.bboxx.domain.model.RequestEmotions
import com.depromeet.bboxx.presentation.model.FeelingEmotionModel
import javax.inject.Inject

class FeelingEmotionMapper @Inject constructor() {
    fun trans(target: RequestEmotions): List<FeelingEmotionModel> = with(target){
        return target.emotionStatuses.map{
            FeelingEmotionModel(
                it.emotionUrl,
                it.id,
                it.status,
                isSelected = false
            )
        }
    }
}