package com.startedup.base.ui.base


import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
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

abstract class BaseActivity : AppCompatActivity(), NetworkStateReceiver.NetworkStateReceiverListener {

    private var mNetworkStateReceiver: NetworkStateReceiver? = null

    private var builder: AlertDialog.Builder? = null

    abstract fun onMultiplePermissionGranted()

    abstract fun onSinglePermissionGranted()

    protected abstract fun onNetworkOn()

    protected abstract fun onNetworkOff()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        registerNetworkState()
    }

    private fun registerNetworkState() {
        mNetworkStateReceiver = NetworkStateReceiver()
        mNetworkStateReceiver!!.addListener(this)
        this.registerReceiver(mNetworkStateReceiver, IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION))
    }

    fun addFragment(fragment: Fragment, @IdRes fragmentContainer: Int, addToBackStack: Boolean) {
        FragmentTransactionUtil.addFragment(supportFragmentManager, fragment, fragmentContainer, addToBackStack)
    }

    fun replaceFragment(fragment: Fragment, @IdRes fragmentContainer: Int,
                        addToBackStack: Boolean) {
        FragmentTransactionUtil.replaceFragment(supportFragmentManager, fragment, fragmentContainer, addToBackStack)
    }

     fun replaceFragment(fragment: Fragment, @IdRes fragmentContainer: Int,
                                  addToBackStack: Boolean, transitionView: View) {
        FragmentTransactionUtil.replaceFragment(supportFragmentManager, fragment, fragmentContainer, addToBackStack, transitionView)
    }

    protected fun requestMultiplePermission(permissionList: ArrayList<String>) {

        PermissionUtil.requestMultiplePermission(this, permissionList, object : PermissionResultListener {
            override fun onAllPermissionGranted() {
                this@BaseActivity.onMultiplePermissionGranted()
            }

            override fun onPermissionGranted() {
                // nothing
            }

            override fun onPermissionPermanentlyDenied() {
                showEnablePermissionDialog()
            }

            override fun onPermissionDenied() {
                //showToastLong(ResourceFinder.Companion.getString(R.string.permission_denied));
            }
        })

    }

    protected fun requestSinglePermission(permission: String) {

        PermissionUtil.requestSinglePermission(this, permission, object : PermissionResultListener {
            override fun onAllPermissionGranted() {
                // nothing
            }

            override fun onPermissionGranted() {
                this@BaseActivity.onSinglePermissionGranted()
            }

            override fun onPermissionPermanentlyDenied() {
                showEnablePermissionDialog()
            }

            override fun onPermissionDenied() {
                //showToastLong(ResourceFinder.Companion.getString(R.string.permission_denied));
            }
        })
    }

    private fun showEnablePermissionDialog() {
        builder = DialogUtils.getAlertDialog(this@BaseActivity, R.string.permission_title, R.string.permission_needed, true)
        var dialog: AlertDialog? = null
        builder!!.setPositiveButton("Open setting") { _, i ->
            val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:" + this@BaseActivity.packageName)
            startActivity(intent)
        }
        val finalDialog = dialog
        builder!!.setNegativeButton("Cancel") { _, i -> finalDialog!!.cancel() }
        dialog = builder!!.create()
        dialog!!.show()
    }

    fun showToastShort(toastMessage: String) {
        CommonUtil.showToasLong(this@BaseActivity, toastMessage)
    }

    fun showToastLong(toastMessage: String) {
        CommonUtil.showToasLong(this@BaseActivity, toastMessage)
    }

    override fun networkAvailable() {
        onNetworkOn()
    }

    override fun networkUnavailable() {
        onNetworkOff()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mNetworkStateReceiver)
    }
}
