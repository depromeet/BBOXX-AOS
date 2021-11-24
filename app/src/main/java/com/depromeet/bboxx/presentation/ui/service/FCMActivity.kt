package com.depromeet.bboxx.presentation.ui.service

import android.os.Bundle
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityFcmBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI
import com.depromeet.bboxx.util.SharedPreferenceUtil
import com.depromeet.bboxx.util.constants.SharedConstants
import com.depromeet.bboxx.util.constants.SharedConstants.C_FCM_MSG_KEY

class FCMActivity: BaseActivity<ActivityFcmBinding>(R.layout.activity_fcm) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fcmMsgValueCheck()
    }

    private fun fcmMsgValueCheck(){
        SharedPreferenceUtil.initSharedPreference(
            this,
            SharedConstants.C_FCM_MSG_SHARED
        )
        val msg = SharedPreferenceUtil.getDataStringSharedPreference(C_FCM_MSG_KEY)
        if(msg?.isNotBlank() == true){
            SharedPreferenceUtil.delSharedPreference()
            NavigatorUI.toMain(this, 0, msg)
        }
    }

}