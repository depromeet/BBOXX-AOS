package com.depromeet.bboxx.data.mapper

import com.depromeet.bboxx.data.entity.ImprovementTagsEntity
import com.depromeet.bboxx.domain.model.ImprovementTags
import javax.inject.Inject

class ImprovementTagsEntityMapper @Inject constructor() {
    fun trans(target: ImprovementTagsEntity): ImprovementTags = with(target) {
        return ImprovementTags(tags)
    }
}