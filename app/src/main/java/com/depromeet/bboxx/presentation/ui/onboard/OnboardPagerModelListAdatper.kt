package com.depromeet.bboxx.presentation.ui.onboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.depromeet.bboxx.R
import com.depromeet.bboxx.presentation.model.OnboardModel
import kotlinx.android.synthetic.main.item_onboard_content.view.*

class OnboardPagerModelListAdatper: PagerAdapter() {

    private var onboardModel: List<OnboardModel> = listOf()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(
            R.layout.item_onboard_content, container, false
        )

        with(view){
            txt_board_title.text = onboardModel[position % onboardModel.size].title
        }

        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return if (onboardModel.isNotEmpty()) {
            Int.MAX_VALUE
        } else {
            0
        }
    }
}