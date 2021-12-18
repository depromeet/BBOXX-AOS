package com.depromeet.bboxx.presentation.ui.decibel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.BuildConfig
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.DecibelResultLayoutBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.feelnote.FeelingNoteSelectFragment
import com.depromeet.bboxx.presentation.utils.CustomTopView
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class DecibelResultFragment(private val dB: Int) : Fragment() {

    lateinit var mainActivity: MainActivity
    var isStatusClick = true
    var colorNumber = 0
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onResume() {
        super.onResume()
        onRestartViewTitleColorChange()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DecibelResultLayoutBinding.inflate(inflater, container, false)

        initView(binding, container)
        return binding.root
    }


    @SuppressLint("ResourceType", "UseCompatLoadingForDrawables", "SetTextI18n")
    private fun initView(binding: DecibelResultLayoutBinding, container: ViewGroup?) {

        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.allClearFragment()
            }
        }, R.drawable.ic_close, resources.getString(R.color.main_bg))
        binding.tvResutDb.text = dB.toString()

        @SuppressLint("SetTextI18n")

        when (dB) {
            in 0..50 -> {
                colorNumber = 1
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_6AA13D)))
                binding.tvResultInfo.text = "내가 너의 말을 들어 줄 수 있는\n친구가 되어 줄게🍃"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_0_50)
                mainActivity.setStatusBarColor(R.color.color_6AA13D)
            }
            in 51..69 -> {
                colorNumber = 2
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_A8BD28)))
                binding.tvResultInfo.text = "괜찮아 괜찮아 \n그럴 떄도 있는거야☁️"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_51_69)
                mainActivity.setStatusBarColor(R.color.color_A8BD28)
            }
            in 70..89 -> {
                colorNumber = 3
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_EF9E24)))
                binding.tvResultInfo.text = "좀더 크게 감정을 표현하고 나면\n기분이 나아질꺼야💥"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_70_89)
                mainActivity.setStatusBarColor(R.color.color_EF9E24)
            }
            in 90..99 -> {
                colorNumber = 4
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_EF9E24)))
                binding.tvResultInfo.text = "잘했어. 속에 있는 건 다 풀어야해.\n불족어때?🔥"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_90_99)
                mainActivity.setStatusBarColor(R.color.color_EF9E24)
            }
            in 100..119 -> {
                colorNumber = 5
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_EF9E24)))
                binding.tvResultInfo.text = "와, 마음 속에 허리케인이\n몰아치고 갔었네🌪"
                mainActivity.setStatusBarColor(R.color.color_EF9E24)
            }
            else -> {
                colorNumber = 6
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_D04141)))
                binding.tvResultInfo.text = "운석이 충돌한 줄 알았어!\n속 시원하게 다 게웠어?☄️"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_120)
                mainActivity.setStatusBarColor(R.color.color_D04141)
            }

        }
        binding.btnAgain.setOnClickListener {
            mainActivity.clearThisFragment(this)
        }

        binding.btGoToFeelingNote.setOnClickListener {
            isStatusClick = false
            mainActivity.addFragment(FeelingNoteSelectFragment(colorNumber))
        }

        binding.btnShare.setOnClickListener {
            try {

                val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA)
                val fileName = "/" + sdf.format(Date()).toString() + ".jpg"
                val mPath = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
                    requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM).toString() + fileName
                } else{
                    Environment.getExternalStorageDirectory().toString() + fileName
                }

                val bitmap = getBitmapFromView(container?.rootView)

                // 이미지 파일 생성
                val imageFile = File(mPath)
                val outputStream = FileOutputStream(imageFile)
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
                outputStream.close()

                Toast.makeText(context, "이미지 저장 완료", Toast.LENGTH_SHORT).show()

                shareJPG(imageFile)

            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    private fun shareJPG(img: File) {

        try {
            val shareIntent = Intent(Intent.ACTION_SEND)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                shareIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                shareIntent.type = "image/*"

                val contentUri = FileProvider.getUriForFile(
                    mainActivity,
                    BuildConfig.APPLICATION_ID + ".provider", img
                )

                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
            } else {
                shareIntent.type = "image/*"
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(img))
            }

            mainActivity.startActivity(Intent.createChooser(shareIntent, ""))

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun getBitmapFromView(view: View?): Bitmap? {
        if (view == null) return null
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    override fun onStop() {
        super.onStop()
        if(isStatusClick){
            mainActivity.setStatusBarColor(R.color.black_80)
        }
        else{
            mainActivity.setStatusBarColor(R.color.main_bg)
        }
    }

    private fun onRestartViewTitleColorChange(){
        when(colorNumber){
            1 -> mainActivity.setStatusBarColor(R.color.color_6AA13D)
            2 -> mainActivity.setStatusBarColor(R.color.color_A8BD28)
            3 -> mainActivity.setStatusBarColor(R.color.color_EF9E24)
            4 -> mainActivity.setStatusBarColor(R.color.color_EF9E24)
            5 -> mainActivity.setStatusBarColor(R.color.color_EF9E24)
            6 -> mainActivity.setStatusBarColor(R.color.color_D04141)
        }
    }
}