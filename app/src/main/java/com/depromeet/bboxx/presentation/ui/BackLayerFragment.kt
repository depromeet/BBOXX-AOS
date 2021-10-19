package com.depromeet.bboxx.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.LayoutToBackBinding


class BackLayerFragment(val bottomFragment: Fragment) : Fragment() {

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

        val binding = LayoutToBackBinding.inflate(inflater, container, false)


        initView(binding)
        return binding.root
    }


    private fun initView(binding: LayoutToBackBinding) {


        binding.clBack.setOnClickListener {
            mainActivity.clearThisFragment(this)
        }

        binding.btnBack.setOnClickListener {
            mainActivity.clearThisFragment(this)
            mainActivity.clearThisFragment(bottomFragment)
        }
        binding.btnCancle.setOnClickListener {
            mainActivity.clearThisFragment(this)
        }

    }


}