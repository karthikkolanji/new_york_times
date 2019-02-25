package com.startedup.base.ui.base

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.View

import com.startedup.base.R
import com.startedup.base.broadcast.NetworkStateReceiver
import com.startedup.base.listener.PermissionResultListener
import com.startedup.base.utils.CommonUtil
import com.startedup.base.utils.DialogUtils
import com.startedup.base.utils.FragmentTransactionUtil
import com.startedup.base.utils.PermissionUtil
import com.startedup.base.utils.ResourceFinder

import java.util.ArrayList

abstract class BaseFragment : Fragment(), NetworkStateReceiver.NetworkStateReceiverListener {

    private var mBaseActivity: BaseActivity? = null
    private var mNetworkStateReceiver: NetworkStateReceiver? = null

    private var builder: AlertDialog.Builder? = null

    protected abstract fun onMultiplePermissionGranted()

    protected abstract fun onSinglePermissionGranted()

    protected abstract fun onNetworkOn()

    protected abstract fun onNetworkOff()


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mBaseActivity = context as BaseActivity?

    }

    override fun onResume() {
        super.onResume()
        registerNetworkState()
    }

    protected fun addFragment(fragment: Fragment, @IdRes fragmentContainer: Int, addToBackStack: Boolean) {
        if (mBaseActivity != null) {
            mBaseActivity!!.addFragment(fragment, fragmentContainer, addToBackStack)
        }
    }

    protected fun replaceFragment(fragment: Fragment, @IdRes fragmentContainer: Int, addToBackStack: Boolean) {
        if (mBaseActivity != null) {
            mBaseActivity!!.replaceFragment(fragment, fragmentContainer, addToBackStack)
        }
    }


    protected fun replaceFragment(fragment: Fragment, @IdRes fragmentContainer: Int, addToBackStack: Boolean,transitionView:View) {
        if (mBaseActivity != null) {
            mBaseActivity!!.replaceFragment(fragment, fragmentContainer, addToBackStack,transitionView)
        }
    }

    protected fun requestMultiplePermission(permissionList: ArrayList<String>) {
        if (mBaseActivity != null) {
            PermissionUtil.requestMultiplePermission(mBaseActivity, permissionList, object : PermissionResultListener {
                override fun onAllPermissionGranted() {
                    mBaseActivity!!.onMultiplePermissionGranted()
                }

                override fun onPermissionGranted() {
                    // nothing
                }

                override fun onPermissionPermanentlyDenied() {
                    showEnablePermissionDialog()
                }

                override fun onPermissionDenied() {
                    //CommonUtil.showToasLong(mBaseActivity, ResourceFinder.Companion.getString(R.string.permission_denied));
                }
            })
        }

    }

    protected fun requestSinglePermission(permission: String) {
        if (mBaseActivity != null) {
            PermissionUtil.requestSinglePermission(mBaseActivity, permission, object : PermissionResultListener {
                override fun onAllPermissionGranted() {
                    // nothing
                }

                override fun onPermissionGranted() {
                    mBaseActivity!!.onSinglePermissionGranted()
                }

                override fun onPermissionPermanentlyDenied() {
                    showEnablePermissionDialog()
                }

                override fun onPermissionDenied() {
                    //showToastLong(ResourceFinder.Companion.getString(R.string.permission_denied));
                }
            })
        }

    }

    private fun showEnablePermissionDialog() {
        builder = DialogUtils.getAlertDialog(mBaseActivity, R.string.permission_title, R.string.permission_needed, true)
        var dialog: AlertDialog? = null
        builder!!.setPositiveButton("Open setting") { dialogInterface, i ->
            val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:" + mBaseActivity!!.packageName)
            startActivity(intent)
        }
        val finalDialog = dialog
        builder!!.setNegativeButton("Cancel") { dialogInterface, i -> finalDialog!!.cancel() }
        dialog = builder!!.create()
        dialog!!.show()
    }

    private fun registerNetworkState() {
        if (mBaseActivity != null) {
            mNetworkStateReceiver = NetworkStateReceiver()
            mNetworkStateReceiver!!.addListener(this)
            mBaseActivity!!.registerReceiver(mNetworkStateReceiver, IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    override fun networkAvailable() {
        onNetworkOn()
    }

    override fun networkUnavailable() {
        onNetworkOff()
    }

    protected fun showToastShort(toastMessage: String) {
        if (mBaseActivity != null) {
            mBaseActivity!!.showToastShort(toastMessage)
        }

    }

    protected fun showToastLong(toastMessage: String) {
        if (mBaseActivity != null) {
            mBaseActivity!!.showToastLong(toastMessage)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mBaseActivity != null) {
            mBaseActivity!!.unregisterReceiver(mNetworkStateReceiver)
        }
    }
}
