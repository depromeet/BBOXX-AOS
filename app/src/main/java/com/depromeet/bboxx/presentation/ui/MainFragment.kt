package com.depromeet.bboxx.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentMainBinding
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.ui.decibel.DecibelFragment
import com.depromeet.bboxx.presentation.ui.growthNote.GrowthNoteFragment
import javax.inject.Inject

class MainFragment @Inject constructor() : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO HAERIN FRAGMNET이동

        binding.btGoToDecibel.setOnClickListener {
            mainActivity.addFragment(DecibelFragment())
        }
        binding.btGoToFeelingNote.setOnClickListener {
//            mainActivity.addFragment(FeelingNoteSelectFragment())
            mainActivity.addFragment(GrowthNoteFragment())
//
        }
//        binding.btGoToDecibel.setOnClickListener {
//            if (container != null) {
//                if (checkSelfPermission(
//                        container.context,
//                        Manifest.permission.RECORD_AUDIO
//                    )
//                    == PackageManager.PERMISSION_GRANTED
//                ) {
////                    val fragment1 = decibelFragment()
////                    childFragmentManager.beginTransaction().add(R.id.fl_main, fragment1).commit()
//                } else {
//                    val fragment2 = permissionRequestFragment()
//                    childFragmentManager.beginTransaction().add(R.id.fl_main, fragment2).commit()
//                    requestPermissions(
//                        arrayOf(Manifest.permission.RECORD_AUDIO),
//                        0
//                    )
//                }
//            }
//        }
    }

}