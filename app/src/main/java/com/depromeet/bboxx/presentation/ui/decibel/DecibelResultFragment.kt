package com.depromeet.bboxx.presentation.ui.decibel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.DecibelResultLayoutBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.feelnote.FeelingNoteSelectFragment
import com.depromeet.bboxx.presentation.utils.CustomTopView
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build

import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import com.depromeet.bboxx.BuildConfig
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*
import com.kakao.auth.StringSet.file


class DecibelResultFragment(val dB: Int) : Fragment() {

    lateinit var mainActivity: MainActivity
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

        val binding = DecibelResultLayoutBinding.inflate(inflater, container, false)

        initView(binding, container)
        return binding.root
    }


    @SuppressLint("ResourceType", "UseCompatLoadingForDrawables")
    private fun initView(binding: DecibelResultLayoutBinding, container: ViewGroup?) {

        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.allClearFragment()
            }
        }, R.drawable.ic_close, resources.getString(R.color.main_bg))
        binding.tvResutDb.text = dB.toString() + "dB"
        //TODO HAERIN 감정 측정도에 맞게 세팅

        @SuppressLint("SetTextI18n")

        when (dB) {
            in 0..50 -> {
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_6AA13D)))
                binding.tvResultInfo.text = "내가 너의 말을 들어 줄 수 있는\n친구가 되어 줄게🍃"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_0_50)
                mainActivity.setStatusBarColor(R.color.color_6AA13D)
            }
            in 51..69 -> {
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_A8BD28)))
                binding.tvResultInfo.text = "괜찮아 괜찮아 \n그럴 떄도 있는거야☁️"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_51_69)
                mainActivity.setStatusBarColor(R.color.color_A8BD28)
            }
            in 70..89 -> {
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_EF9E24)))
                binding.tvResultInfo.text = "좀더 크게 감정을 표현하고 나면\n기분이 나아질꺼야💥"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_70_89)
                mainActivity.setStatusBarColor(R.color.color_EF9E24)
            }
            in 90..99 -> {
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_EF9E24)))
                binding.tvResultInfo.text = "잘했어. 속에 있는 건 다 풀어야해.\n불족어때?🔥"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_90_99)
                mainActivity.setStatusBarColor(R.color.color_EF9E24)
            }
            in 100..119 -> {
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_EF9E24)))
                binding.tvResultInfo.text = "와, 마음 속에 허리케인이\n몰아치고 갔었네🌪"
                mainActivity.setStatusBarColor(R.color.color_EF9E24)
            }
            else -> {
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
            mainActivity.addFragment(FeelingNoteSelectFragment())
        }

        binding.btnShare.setOnClickListener {
            try {
                val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA)
                val fileName = "/" + sdf.format(Date()).toString() + ".jpg"
                val mPath =
                    Environment.getExternalStorageDirectory().toString() + fileName

                val bitmap = getBitmapFromView(container?.rootView)

                // 이미지 파일 생성
                val imageFile = File(mPath)
                val outputStream = FileOutputStream(imageFile)
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();

                Toast.makeText(context, "해당 화면이 캡쳐되었습니다 :)", Toast.LENGTH_SHORT).show()

                //확인 후 추가
//                shareJPG(imageFile)

            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
    }
//
//    private fun shareJPG(img : File){
//
//        try {
//            val shareIntent = Intent(Intent.ACTION_SEND)
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
////                val contentUri = Uri.fromFile(img)
//                shareIntent.type = "image/*";
//
//                val contentUri = FileProvider.getUriForFile(mainActivity,
//                    BuildConfig.APPLICATION_ID + ".provider", img);
////                val contentUri = FileProvider.getUriForFile(mainActivity, , file)
//
//                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
//            } else {
//
//                shareIntent.type = "image/*";
//                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(img));
//
//            }
//
//
//            mainActivity.startActivity(Intent.createChooser(shareIntent, ""));
//
//        }catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }


    private fun getBitmapFromView(view: View?): Bitmap? {
        if (view == null) return null
        var bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }


    override fun onStop() {
        super.onStop()
        mainActivity.setStatusBarColor(R.color.main_bg)
    }

}