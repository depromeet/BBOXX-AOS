package com.depromeet.bboxx.presentation.ui.feelnote

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.EmotionDiarySelectLayoutBinding
import com.depromeet.bboxx.presentation.ui.MainActivity

class FeelingNoteSelectFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    var selectedFeeling: String = ""
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = EmotionDiarySelectLayoutBinding.inflate(inflater, container, false)

        if (container != null) {
            initView(binding, container.context)
        }


        return binding.root
    }


    fun initView(binding: EmotionDiarySelectLayoutBinding, ctx: Context) {
        //TODO HAERIN Array로 빼기
        val items = arrayOf("직장문제", "학업문제", "취업문제", "가족문제", "대인관계", "다른문제", "선택하기")


        var isActivated = false

        val myAapter =
            object : ArrayAdapter<String>(ctx, com.depromeet.bboxx.R.layout.item_spinner) {

                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                    val view = super.getView(position, convertView, parent)

                    if (position == count) {

                        view.findViewById<TextView>(com.depromeet.bboxx.R.id.tv_item).text = ""
                        view.findViewById<TextView>(com.depromeet.bboxx.R.id.tv_item).hint =
                            getItem(count)

                    }
                    return view
                }

                override fun getCount(): Int {
                    return super.getCount() - 1
                }

            }

        myAapter.addAll(items.toMutableList())

        binding.spFeeling.adapter = myAapter
        binding.spFeeling.setSelection(myAapter.count)


        binding.spFeeling.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if (position != myAapter.count) {
                    setBtn(binding.btnSuccess)
                    isActivated = true
                    selectedFeeling = items[position]
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.btnSuccess.setOnClickListener {
            if (isActivated) {

                mainActivity.addFragment(FeelingNoteFragment(selectedFeeling))
            }
        }

    }

    fun setBtn(bt: TextView) {
        bt.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
        bt.setTextColor(Color.parseColor("#FFFFFF"))
    }
}

