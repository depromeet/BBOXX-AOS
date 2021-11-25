package com.depromeet.bboxx.presentation.ui.feelhistory

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentAlarmBinding
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.model.NotificationModel
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.growthNote.GrowthNoteReViewFeelingNote
import com.depromeet.bboxx.presentation.utils.CustomTopView
import org.jetbrains.anko.runOnUiThread


class FeelingHistoryFragment : Fragment(), UserClickEvent {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    private var isDelete = false

    private val feelHistoryAdapter: FeelHistoryAdapter by lazy {
        FeelHistoryAdapter()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlarmBinding.inflate(inflater, container, false)

        mainActivity.requestFeelHistoryList()

        if (container != null) {
            initView(binding, container.context)
        }

        return binding.root
    }

    fun initView(binding: FragmentAlarmBinding, ctx: Context) {

        topViewInit(binding)
        setAdapterAndRecyclerViewInit(binding)

        mainActivity.feelHistoryViewModel.noticeList.observeNonNull(this) {
            if (it.isNotEmpty()) {
                feelHistoryAdapter.replaceItems(it.toMutableList())
                binding.rvAlarmHistory.isVisible = true
                binding.txtAlarmTitle.isVisible = false
                binding.imgAlarmNo.isVisible = false
            }
        }
    }

    private fun setAdapterAndRecyclerViewInit(binding: FragmentAlarmBinding) {

        binding.rvAlarmHistory.run {
            adapter = feelHistoryAdapter
            setHasFixedSize(false)

            ItemTouchHelper(object :
                ItemTouchHelper.SimpleCallback(0, 0) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                }
            }).attachToRecyclerView(this)
        }

        feelHistoryAdapter.setOnClickListener(this)
    }

    @SuppressLint("ResourceType")
    private fun topViewInit(binding: FragmentAlarmBinding) {
        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.clearThisFragment(this@FeelingHistoryFragment)
            }
        }, resources.getString(R.color.black))

        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {

                if (!isDelete) {
                    feelHistoryAdapter.deleteStatusVisible()

                    mainActivity.applicationContext.runOnUiThread {
                        binding.clTopView.setRightBtnImageChange(R.drawable.ic_check)
                    }
                } else {
                    feelHistoryAdapter.deleteStatusGone()
                    mainActivity.applicationContext.runOnUiThread {
                        binding.clTopView.setRightBtnImageRecover(R.drawable.ic_trash)
                    }
                }

                isDelete = !isDelete
            }
        }, R.drawable.ic_trash, resources.getString(R.color.main_bg))
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onItemClick(notifications: NotificationModel, position: Int) {
        if (!isDelete) {
            mainActivity.addFragment(GrowthNoteReViewFeelingNote(notifications.emotionDiaryId))
        }
    }

    override fun onItemDeleteClick(notifications: NotificationModel, position: Int) {
        // 감정 삭제
        mainActivity.deleteFeelData(notifications.emotionDiaryId)
        feelHistoryAdapter.notifyItemRemoved(position)
    }
}