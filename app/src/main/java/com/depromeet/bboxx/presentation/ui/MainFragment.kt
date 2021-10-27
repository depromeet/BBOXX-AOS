package com.depromeet.bboxx.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.FragmentMainBinding
import com.depromeet.bboxx.presentation.ui.decibel.DecibelFragment
import com.depromeet.bboxx.presentation.ui.feelnote.FeelingNoteSelectFragment
import com.depromeet.bboxx.presentation.ui.growthNote.GrowthNoteFragment


class MainFragment : Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMainBinding.inflate(inflater, container, false)

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


        return binding.root
    }


}