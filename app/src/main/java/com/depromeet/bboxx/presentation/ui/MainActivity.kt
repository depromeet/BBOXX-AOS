package com.depromeet.bboxx.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
//                != PackageManager.PERMISSION_GRANTED) {
//
//                ActivityCompat.requestPermissions(
//                    this, arrayOf(
//                        Manifest.permission.RECORD_AUDIO
//                    ),0)
//
//            }else{
//                val fragment1 = decibelFragment()
//                childFragmentManager.be
//                supportFragmentManager.beginTransaction().replace(R.id.fl_main, fragment1).commit();
//            }

        val fragment1 = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fl_main, fragment1).commit();
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.fl_main, fragment).commit();

    }

    fun addTopFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.anim_up, R.anim.anim_down)
        supportFragmentManager.beginTransaction().add(R.id.fl_main, fragment).commit()
    }

    fun clearThisFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }
}