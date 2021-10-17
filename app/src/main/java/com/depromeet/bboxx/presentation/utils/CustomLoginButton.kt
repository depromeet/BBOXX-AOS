package com.depromeet.bboxx.presentation.utils

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.depromeet.bboxx.R

class CustomLoginButton : LinearLayout {
    var bg: ConstraintLayout? = null
    var symbol: ImageView? = null
    var text: TextView? = null

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        getAttrs(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs) {
        initView()
        getAttrs(attrs, defStyle)
    }

    private fun initView() {
        val infService = Context.LAYOUT_INFLATER_SERVICE
        val li = context.getSystemService(infService) as LayoutInflater
        val v: View = li.inflate(R.layout.view_login_button, this, false)
        addView(v)
        bg = findViewById<View>(R.id.bg) as ConstraintLayout
        symbol = findViewById<View>(R.id.symbol) as ImageView
        text = findViewById<View>(R.id.text) as TextView
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginButton)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginButton, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val bg_resID =
            typedArray.getResourceId(R.styleable.LoginButton_bg, R.drawable.bg_btn_kakao_login)
        bg!!.setBackgroundResource(bg_resID)
        val symbol_resID =
            typedArray.getResourceId(R.styleable.LoginButton_symbol, R.drawable.ic_kakao)
        symbol!!.setImageResource(symbol_resID)
        //symbol!!.setColorFilter(Color.WHITE)
        val textColor = typedArray.getColor(R.styleable.LoginButton_textColor, 0)
        text!!.setTextColor(textColor)
        val text_string = typedArray.getString(R.styleable.LoginButton_text)
        text!!.text = text_string
        text!!.textAlignment = View.TEXT_ALIGNMENT_CENTER
        typedArray.recycle()
    }

    fun setBg(bg_resID: Int) {
        bg!!.setBackgroundResource(bg_resID)
    }

    fun setSymbol(symbol_resID: Int) {
        symbol!!.setImageResource(symbol_resID)
    }

    fun setTextColor(color: Int) {
        text!!.setTextColor(color)
    }

    fun setText(text_string: String?) {
        text!!.text = text_string
    }

    fun setText(text_resID: Int) {
        text!!.setText(text_resID)
    }
}