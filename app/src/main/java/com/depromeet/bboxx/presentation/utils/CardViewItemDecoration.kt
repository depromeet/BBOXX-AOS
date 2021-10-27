package com.depromeet.bboxx.presentation.utils

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class CardViewItemDecoration(context: Context, val itemCount : Int) : ItemDecoration() {
    private val margin30: Int
    private val margin16: Int

    // dp -> pixel 단위로 변경
    private fun dpToPx(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        )
            .toInt()
    }

    init {
        margin30 = dpToPx(context, 30)
        margin16 = dpToPx(context, 16)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.left = margin30
                outRect.right = margin16
            }
            itemCount - 1 -> {
                outRect.right = margin30
            }
            else -> {
                outRect.right = margin16
            }
        }
    }

}