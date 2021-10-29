package com.depromeet.bboxx.presentation.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.depromeet.bboxx.R

class CustomTopView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs){

    private var backButton: ImageView
    private var rightButton: ImageView
    private var redoButton: ConstraintLayout


    init {
        val v = View.inflate(context, R.layout.layout_top_view, this)
        backButton = v.findViewById(R.id.btn_back)
        rightButton = v.findViewById(R.id.btn_right)
        redoButton = v.findViewById(R.id.cl_redo)
        backButton.visibility = View.GONE
        rightButton.visibility = View.GONE
        redoButton.visibility = View.GONE
    }



    fun setBackBtn(callback : OnclickCallback, color : String? = null){
        backButton.visibility = View.VISIBLE

        color?.let{
//            backButton.backgroundTintMode
            backButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor(it))

//            backButton.setColorFilter(Color.parseColor(it))
        }

        backButton.setOnClickListener {
            callback.callback()
        }
    }

    fun setRightBtn(callback : OnclickCallback, drawable : Int, color : String? = null){
        rightButton.visibility = View.VISIBLE
        rightButton.background = resources.getDrawable(drawable)

        color?.let{
            rightButton.setColorFilter(Color.parseColor(it))
        }

        rightButton.setOnClickListener {
            callback.callback()
        }
    }

    fun setRightBtnImageChange(drawable : Int){
        backButton.visibility = View.GONE
        rightButton.background = resources.getDrawable(drawable)
    }

    fun setRightBtnImageRecover(drawable: Int){
        backButton.visibility = View.VISIBLE
        rightButton.background = resources.getDrawable(drawable)
    }

    fun setRedoBtn(callback: OnclickCallback){
        redoButton.visibility = View.VISIBLE
        redoButton.setOnClickListener {
            callback.callback()
        }
    }


    interface OnclickCallback{
        fun callback()
    }

}