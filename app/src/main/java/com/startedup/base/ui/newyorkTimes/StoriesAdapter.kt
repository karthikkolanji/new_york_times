package com.startedup.base.ui.newyorkTimes

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.startedup.base.R
import com.startedup.base.di.module.GlideApp
import com.startedup.base.listener.CallBacks
import com.startedup.base.model.times.ResultsItem
import kotlinx.android.synthetic.main.item_stories.view.*

class StoriesAdapter constructor(private val resultsItem: List<ResultsItem>?
                                 ,private val callBacks: CallBacks)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {

        return StoriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_stories,parent,false))
    }

    override fun getItemCount(): Int {
        return resultsItem?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder=holder as StoriesViewHolder
        viewHolder.bindViews(position)
    }

    internal inner class StoriesViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView) {

        @SuppressLint("CheckResult")
        fun bindViews(position: Int){
            var result= resultsItem?.get(position)
            try {
                GlideApp.with(itemView.context).load(result?.multimedia?.get(0)?.url).into(itemView.ivThumb)
            }
            catch (e:Exception){
                Log.d("multimedia","${e.localizedMessage} exception at position $position")
            }
            itemView.tvArticleTitle.text=result?.title
            itemView.tvAuthorName.text=result?.byline
            itemView.tvSection.text=result?.section


            itemView.setOnClickListener {
                var result= resultsItem?.get(layoutPosition)
                callBacks.onItemClicked(result)
            }
        }


    }



}