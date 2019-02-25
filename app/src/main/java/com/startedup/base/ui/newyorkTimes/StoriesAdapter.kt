package com.startedup.base.ui.newyorkTimes

import android.annotation.SuppressLint
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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

    private var lastPosition: Int=-1

    internal inner class StoriesViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView) {

        @SuppressLint("CheckResult")
        fun bindViews(position: Int){
            val result= resultsItem?.get(position)
            try {
                GlideApp.with(itemView.context).load(result?.multimedia?.get(2)?.url).error(R.drawable.im_something_went_wrong).centerCrop().into(itemView.ivThumb)
            }
            catch (e:Exception){
                GlideApp.with(itemView.context).load(R.drawable.im_something_went_wrong).centerInside().into(itemView.ivThumb)
                Log.d("multimedia","${e.localizedMessage} exception at position $position")
            }
            itemView.tvArticleTitle.text=result?.title
            itemView.tvAuthorName.text=result?.byline

            enterAnimation(itemView.cvRoot,position)

            itemView.setOnClickListener {
                var result= resultsItem?.get(layoutPosition)
                callBacks.onItemClicked(result,itemView.ivThumb)
            }
        }

        private fun enterAnimation(cardView: CardView, position: Int) {
            if (position > lastPosition) {
                val animation = AnimationUtils.loadLayoutAnimation(cardView.context,
                        R.anim.layout_animation_fall_down)
                cardView.layoutAnimation = animation
                lastPosition = position
            }
        }
    }
}