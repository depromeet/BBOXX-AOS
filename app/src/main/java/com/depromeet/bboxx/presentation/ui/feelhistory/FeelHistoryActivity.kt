//package com.depromeet.bboxx.presentation.ui.feelhistory
//
//import android.os.Bundle
//import androidx.activity.viewModels
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.depromeet.bboxx.R
//import com.depromeet.bboxx.databinding.ActivityAlarmBinding
//import com.depromeet.bboxx.domain.model.Notifications
//import com.depromeet.bboxx.presentation.base.BaseActivity
//import com.depromeet.bboxx.presentation.extension.observeNonNull
//import com.depromeet.bboxx.presentation.viewmodel.FeelHistoryViewModel
//import dagger.hilt.android.AndroidEntryPoint
//import javax.inject.Inject
//
//@AndroidEntryPoint
//class FeelHistoryActivity: BaseActivity<ActivityAlarmBinding>(R.layout.activity_alarm), UserClickEvent {
//
//    private val feelHistoryViewModel : FeelHistoryViewModel by viewModels()
//
//    @Inject
//    lateinit var feelHistoryAdapter: FeelHistoryAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setAdapterAndRecyclerViewInit()
//
//        feelHistoryViewModel.noticeList.observeNonNull(this){
//            feelHistoryAdapter.replaceItems(it)
//        }
//    }
//
//    private fun setAdapterAndRecyclerViewInit(){
//        binding.rvAlarmHistory.run{
//            adapter = feelHistoryAdapter
//            setHasFixedSize(false)
//            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
//
//            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
//                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//                    return false
//                }
//
//                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                    if (viewHolder is FeelHistoryAdapter.NotificationInformationHolder) {
//
//                    }
//                }
//            }).attachToRecyclerView(this)
//        }
//
//        feelHistoryAdapter.setOnClickListener(this)
//    }
//
//    override fun onItemClick(notifications: Notifications) {
//        //  상세 보기
//    }
//
//    override fun onItemDeleteClick(notifications: Notifications) {
//        //  삭제
//        feelHistoryViewModel.deleteEmotion(notifications.emotionDiaryId)
//    }
//
//
//}