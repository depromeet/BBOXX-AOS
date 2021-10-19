package com.depromeet.bboxx.presentation.ui.onboard.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.depromeet.bboxx.R
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.viewmodel.OnboardViewModel
import kotlinx.android.synthetic.main.fragment_onboard_four.*

class OnboardFourFragment: BaseFragment(R.layout.fragment_onboard_four) {

    private val onboardViewModel: OnboardViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                OnboardViewModel() as T
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt_next_step.setOnClickListener {
            onboardViewModel.onNextStepEvent()
        }
    }
}