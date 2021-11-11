package com.depromeet.bboxx.data.mapper

import android.annotation.SuppressLint
import com.depromeet.bboxx.data.entity.EmotionDiaryEntity
import com.depromeet.bboxx.domain.model.EmotionDiary
import com.depromeet.bboxx.util.DateFormatter
import javax.inject.Inject

class EmotionDiaryEntityMapper @Inject constructor(
    private val emotionStatusEntityMapper: EmotionStatusEntityMapper
) {
    @SuppressLint("NewApi")
    fun trans(target: EmotionDiaryEntity): EmotionDiary = with(target) {
        val createDate = this.createdAt.substring(0, this.createdAt.indexOf("."))

        return EmotionDiary(
            this.categoryId,
            this.content,
            DateFormatter().formatFormatterEmotion(createDate),
            emotionStatusEntityMapper.transList(this.emotionStatusList),
            this.id,
            this.memberId,
            this.title,
            this.updateAt?:""
        )
    }
}