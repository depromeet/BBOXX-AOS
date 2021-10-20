package com.depromeet.bboxx.presentation.ui.feelhistory

import android.os.Bundle
import androidx.activity.viewModels
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityAlarmBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.viewmodel.FeelHistoryViewModel

class FeelHistoryActivity: BaseActivity<ActivityAlarmBinding>(R.layout.activity_alarm) {

    private val feelHistoryViewModel : FeelHistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}