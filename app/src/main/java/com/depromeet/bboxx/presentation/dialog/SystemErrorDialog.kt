package com.depromeet.bboxx.presentation.dialog

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.depromeet.bboxx.R
import kotlinx.android.synthetic.main.dialog_system_error.*

class SystemErrorDialog(context: Context,
                        private val confirmClick: () -> Unit
) : AppCompatDialog(context, R.style.AppTheme_DimDialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
        setContentView(R.layout.dialog_system_error)

        tv_confirm.setOnClickListener {
            confirmClick()
        }

    }
}