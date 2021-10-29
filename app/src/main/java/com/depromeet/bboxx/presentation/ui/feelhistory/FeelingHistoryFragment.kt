package com.depromeet.bboxx.presentation.ui.feelhistory

import android.content.Context
import android.os.Bundle
import android.view.View
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
import org.jetbrains.anko.runOnUiThread
import javax.inject.Inject

//  프래그먼트로 전환 예정
class FeelingHistoryFragment @Inject constructor()
    :BaseFragment<FragmentAlarmBinding>(R.layout.fragment_alarm), UserClickEvent{

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    private var isDelete = false

    private val feelHistoryAdapter: FeelHistoryAdapter by lazy {
        FeelHistoryAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapterAndRecyclerViewInit()

        topViewInit()

        val dataList = ArrayList<Notifications>()
        val notifications1 = Notifications(
            "10.23",0,0, "string",0,"SENT", "자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬 한번 읽어볼래? 자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬한번 읽어볼래","7일전"
        )

        val notifications2 = Notifications(
            "10.24",0,0, "string",0,"SENT", "자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬 한번 읽어볼래? 자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬한번 읽어볼래","6일전"
        )

        val notifications3 = Notifications(
            "10.25",0,0, "string",0,"SENT", "자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬 한번 읽어볼래? 자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬한번 읽어볼래","5일전"
        )

        val notifications4 = Notifications(
            "10.26",0,0, "string",0,"SENT", "자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬 한번 읽어볼래? 자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬한번 읽어볼래","4일전"
        )

        val notifications5 = Notifications(
            "10.27",0,0, "string",0,"SENT", "자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬 한번 읽어볼래? 자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬한번 읽어볼래","3일전"
        )

        val notifications6 = Notifications(
            "10.28",0,0, "string",0,"SENT", "자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬 한번 읽어볼래? 자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬한번 읽어볼래","2일전"
        )

        val notifications7 = Notifications(
            "10.29",0,0, "string",0,"SENT", "자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬 한번 읽어볼래? 자이언트펭귄! 한달 전(2021년 10월 20일)에 쓴 일기가 도착했어 📬한번 읽어볼래","1일전"
        )

        dataList.add(notifications1)
        dataList.add(notifications2)
        dataList.add(notifications3)
        dataList.add(notifications4)
        dataList.add(notifications5)
        dataList.add(notifications6)
        dataList.add(notifications7)

        feelHistoryAdapter.replaceItems(dataList)
        binding.rvAlarmHistory.isVisible = true
        binding.txtAlarmTitle.isVisible = false
        binding.imgAlarmNo.isVisible = false

        mainActivity.feelHistoryViewModel.noticeList.observeNonNull(this){
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
                mainActivity.clearThisFragment(this@FeelingHistoryFragment)
            }
        }, resources.getString(R.color.black) )

        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {


                if(!isDelete){
                    mainActivity.applicationContext.runOnUiThread {
                        binding.clTopView.setRightBtnImageChange(R.drawable.ic_check)
                    }
                }else{
                    mainActivity.applicationContext.runOnUiThread {
                        binding.clTopView.setRightBtnImageRecover(R.drawable.ic_trash)
                    }
                }

                isDelete = !isDelete
            }
        }, R.drawable.ic_trash, resources.getString(R.color.main_bg))
    }

    override fun onItemClick(notifications: Notifications, position: Long) {
        //  상세 보기
        if(isDelete){
            feelHistoryAdapter.notifyItemRemoved(position.toInt())
        }
    }

    override fun onItemDeleteClick(notifications: Notifications, position: Long) {
        feelHistoryAdapter.notifyItemRemoved(position.toInt())
        //  삭제
       /// mainActivity.feelHistoryViewModel.deleteEmotion(notifications.emotionDiaryId)
    }
}