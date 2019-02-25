package com.startedup.base.ui.newyorkTimes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.startedup.base.R
import com.startedup.base.api.Status
import com.startedup.base.di.Injectable
import com.startedup.base.listener.CallBacks
import com.startedup.base.model.times.ResultsItem
import com.startedup.base.model.times.TimesStoriesResponse
import kotlinx.android.synthetic.main.include_error.*
import kotlinx.android.synthetic.main.include_loading.*
import kotlinx.android.synthetic.main.times_list_fragment.*
import javax.inject.Inject


private const val ARG_SECTION="section"

class TimesListFragment : Fragment(),Injectable,CallBacks {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    var viewModel: TimesViewModel?=null

    private lateinit var section:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            section= it?.getString(ARG_SECTION).toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.times_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initData()
        initListeners()
    }

    private fun initListeners() {
        ivError.setOnClickListener { onRetry() }
        swipeRefresh.setOnRefreshListener { initData()
        swipeRefresh.isRefreshing=false}
    }

    private fun initViewModel() {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(section,TimesViewModel::class.java)

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (!isVisibleToUser){
            if (viewModel!=null){
                viewModel=null
            }
        }
    }

    private fun initData() {
        viewModel?.fetchStories(section)?.observe(this, Observer {

            when (it?.status) {

                Status.LOADING -> {
                    showLoading(true)
                    showError(false,null)
                }


                Status.SUCCESS -> {
                    showLoading(false)
                    showError(false,null)
                    showSuccessData(it.data)
                }

                Status.ERROR -> {
                    showLoading(false)
                    showError(true,it.message)
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
    override fun onItemClicked(resultItems: ResultsItem?) {


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
