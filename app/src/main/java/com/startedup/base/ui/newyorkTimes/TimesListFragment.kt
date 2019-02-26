package com.startedup.base.ui.newyorkTimes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.startedup.base.R
import com.startedup.base.api.Status
import com.startedup.base.constants.IntentExtraConstant
import com.startedup.base.di.Injectable
import com.startedup.base.listener.CallBacks
import com.startedup.base.model.times.ResultsItem
import com.startedup.base.model.times.TimesStoriesResponse
import com.startedup.base.ui.base.BaseFragment
import kotlinx.android.synthetic.main.include_error.*
import kotlinx.android.synthetic.main.include_loading.*
import kotlinx.android.synthetic.main.times_list_fragment.*
import javax.inject.Inject


private const val ARG_SECTION="section"

class TimesListFragment : BaseFragment(),Injectable,CallBacks {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    var viewModel: TimesViewModel?=null

    private lateinit var section:String

    private var mData:TimesStoriesResponse?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
        arguments.let {
            section= it?.getString(ARG_SECTION).toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        initViewModel()
        return inflater.inflate(R.layout.times_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListeners()
    }

    private fun initListeners() {
        ivError.setOnClickListener { onRetry() }
        swipeRefresh.setOnRefreshListener {
            mData=null
           observerViewModel()
        swipeRefresh.isRefreshing=false
        }
    }

    private fun initViewModel() {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(section,TimesViewModel::class.java)

    }

    private fun initData() {
        if (mData==null) observerViewModel()
        else showSuccessData(mData)
    }

    private fun observerViewModel() {
        viewModel?.fetchStories(section)?.observe(this, Observer {

            when (it?.status) {

                Status.LOADING -> {
                    showLoading(true)
                    showError(false, null)
                }


                Status.SUCCESS -> {
                    showLoading(false)
                    showError(false, null)
                    mData = it.data
                    showSuccessData(mData)
                }

                Status.ERROR -> {
                    showLoading(false)
                    showError(true, it.message)
                }

            }
        })
    }

    override fun showError(isError:Boolean,errorMessage: String?) {
        if (isError){
            ivError.visibility=View.VISIBLE
            tvErrorMessage.visibility=View.VISIBLE
            tvErrorMessage.text=errorMessage
        }
        else{
            ivError.visibility=View.GONE
            tvErrorMessage.visibility=View.GONE
        }
    }

    override fun showLoading(isLoading: Boolean) {
        if (isLoading){
            pbLoading.visibility=View.VISIBLE
            tvLoadingMessage.visibility=View.VISIBLE
        }
        else{
            pbLoading.visibility=View.GONE
            tvLoadingMessage.visibility=View.GONE
        }
    }

    override fun showSuccessData(data: TimesStoriesResponse?) {
        recyclerView.adapter=StoriesAdapter(data?.results,this)
    }

    override fun onRetry() {
        initData()
    }
    override fun onItemClicked(data: ResultsItem?,transitionView: View) {
        replaceFragment(TimesDetailsFragment.newInstance(data),
                R.id.fragment_container,true,transitionView)
    }


    override fun onMultiplePermissionGranted() {
    }
    override fun onSinglePermissionGranted() {
    }
    override fun onNetworkOn() {
    }
    override fun onNetworkOff() {
    }

    companion object {
        @JvmStatic
        fun newInstance(section: String) =
                TimesListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_SECTION, section)
                        Log.d("ARG_SECTION",section)
                    }
                }
    }
}
