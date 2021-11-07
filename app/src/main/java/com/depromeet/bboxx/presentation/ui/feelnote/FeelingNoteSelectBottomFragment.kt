package com.depromeet.bboxx.presentation.ui.feelnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.BottomFeelingNoteSelectBinding
import com.depromeet.bboxx.presentation.model.FeelingName
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FeelingNoteSelectBottomFragment(val item: (String)-> Unit, val categoryId: (Int) -> Unit): BottomSheetDialogFragment(){

    private val feelingNoteAdapter: FeelingNoteSelectBottomAdapter by lazy{
        FeelingNoteSelectBottomAdapter{
            item(it)
            dismiss()
        }
    }

    private lateinit var binding: BottomFeelingNoteSelectBinding
    private val feelingNameList = mutableListOf<FeelingName>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.bind(
            inflater.inflate(
                R.layout.bottom_feeling_note_select,
                container,
                false
            )
        )!!

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapterInit()
        setAdapterListData()
    }

    private fun setAdapterInit(){
        feelingNoteAdapter.notifyDataSetChanged()

        binding.ryFeel.run{
            adapter = feelingNoteAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun setAdapterListData(){
        resources.getStringArray(R.array.feel).forEach {
            feelingNameList.add(FeelingName(it))
        }

        feelingNoteAdapter.setFeelList(feelingNameList)

    }

}