package com.startedup.base.utils

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewCompat
import android.view.View

/**
 * Created by hrskrs on 7/14/2016.
 */
class FragmentTransactionUtil private constructor() {

    init {
        throw AssertionError()
    }

    companion object {

        fun addFragment(fragmentManager: FragmentManager,
                        fragment: Fragment,
                        @IdRes fragmentContainerId: Int,
                        addToBackStack: Boolean) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(fragmentContainerId, fragment)
            if (addToBackStack) {
                transaction.addToBackStack(fragment.javaClass.simpleName)
            }
            transaction.commit()
        }

        fun replaceFragment(fragmentManager: FragmentManager,
                            fragment: Fragment,
                            @IdRes fragmentContainerId: Int,
                            addToBackStack: Boolean) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(fragmentContainerId, fragment)
            if (addToBackStack) {
                transaction.addToBackStack(fragment.javaClass.simpleName)
            }
            transaction.commit()
        }

        fun replaceFragment(fragmentManager: FragmentManager,
                                                 fragment: Fragment,
                                                 @IdRes fragmentContainerId: Int,
                                                 addToBackStack: Boolean,
                                                 transitionView:View) {
            val transaction = fragmentManager.beginTransaction()
            transaction.apply {
                ViewCompat.getTransitionName(transitionView)?.let { addSharedElement(transitionView, it) }
                        replace(fragmentContainerId, fragment)
                if (addToBackStack) {
                    transaction.addToBackStack(fragment.javaClass.simpleName)
                }
                commit()
            }


        }
    }
}
