package com.depromeet.bboxx.presentation.ui.feelhistory

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentAlarmBinding
import com.depromeet.bboxx.domain.model.Notifications
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.presentation.viewmodel.FeelHistoryViewModel
import javax.inject.Inject

//  프래그먼트로 전환 예정
class FeelingHistoryFragment @Inject constructor()
    :BaseFragment<FragmentAlarmBinding>(R.layout.fragment_alarm), UserClickEvent{

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    private val feelHistoryViewModel : FeelHistoryViewModel by viewModels()

    @Inject
    lateinit var feelHistoryAdapter: FeelHistoryAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapterAndRecyclerViewInit()

        topViewInit()

        val dataList = ArrayList<Notifications>()
        val notifications = Notifications(
            "2021-10-28T15:28:35.324Z",0,0, "string",0,"SENT", "string","2021-10-28T15:28:35.324Z"
        )
        dataList.add(notifications)
        dataList.add(notifications)
        dataList.add(notifications)
        feelHistoryAdapter.replaceItems(dataList)
        binding.rvAlarmHistory.isVisible = true
        binding.txtAlarmTitle.isVisible = false
        binding.imgAlarmNo.isVisible = false

        feelHistoryViewModel.noticeList.observeNonNull(this){
            if(it.isNotEmpty()){
                //feelHistoryAdapter.replaceItems(it)
                binding.txtAlarmTitle.isVisible = false
                binding.imgAlarmNo.isVisible = false
            }
        }
    }

    private fun setAdapterAndRecyclerViewInit(){
        binding.rvAlarmHistory.run{
            adapter = feelHistoryAdapter
            setHasFixedSize(false)

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    if (viewHolder is FeelHistoryAdapter.NotificationInformationHolder) {

                    }
                }
            }).attachToRecyclerView(this)
        }

        feelHistoryAdapter.setOnClickListener(this)
    }

    private fun topViewInit(){
        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback{
            override fun callback() {

            }
        }, resources.getString(R.color.black) )

        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {

            }
        }, R.drawable.ic_trash, resources.getString(R.color.main_bg))
    }

    override fun onItemClick(notifications: Notifications) {
        //  상세 보기
    }

    override fun onItemDeleteClick(notifications: Notifications) {
        //  삭제
        feelHistoryViewModel.deleteEmotion(notifications.emotionDiaryId)
    }
}