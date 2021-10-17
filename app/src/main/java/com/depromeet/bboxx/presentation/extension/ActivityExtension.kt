package com.depromeet.bboxx.presentation.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.transaction

fun AppCompatActivity.replaceFragment(layoutId: Int, fragment: Fragment, tag: String, isAddBackStack: Boolean = false) {
    supportFragmentManager.transaction {
        if (isAddBackStack) {
            addToBackStack(tag)
        }
        replace(layoutId, fragment, tag)
    }
}

inline fun FragmentManager.transaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commitAllowingStateLoss()
}