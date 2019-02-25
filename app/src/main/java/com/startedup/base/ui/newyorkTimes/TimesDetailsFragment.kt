package com.startedup.base.ui.newyorkTimes


import android.os.Build
import android.os.Bundle
import android.support.transition.TransitionInflater
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestListener
import com.startedup.base.di.Injectable
import com.startedup.base.di.module.GlideApp
import com.startedup.base.model.times.ResultsItem
import java.lang.Exception
import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import android.view.ViewTreeObserver
import com.startedup.base.R
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_stories.view.*


private const val ARG_RESULT = "result"


class TimesDetailsFragment : Fragment(),Injectable {
    private var resultDetails: ResultsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = android.transition.TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        }
        arguments?.let {
            resultDetails = it.getParcelable(ARG_RESULT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.startedup.base.R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayData()
    }

    private fun displayData() {
        try {
            GlideApp.with(ivCoverImage.context).
                    load(resultDetails?.multimedia?.get(2)?.url)
                    .centerCrop()
                    .dontAnimate()
                    .listener(object :  RequestListener<Drawable> {
                        override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            startPostponedEnterTransition()
                            return false
                        }

                        override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                           startPostponedEnterTransition()
                            return false
                        }
                    })
                    .into(ivCoverImage)

        }
        catch (e:Exception){
            GlideApp.with(ivCoverImage)
                    .load(R.drawable.im_something_went_wrong)
                    .centerInside()
                    .into(ivCoverImage)
        }

        tvTitle.text=resultDetails?.title
        tvName.text=resultDetails?.byline
        tvAbstract.text=resultDetails?.jsonMemberAbstract
        tvPublishDate.text=resultDetails?.publishedDate
        tvArticleLink.text=resultDetails?.shortUrl

    }


    companion object {
        @JvmStatic
        fun newInstance(resultItems: ResultsItem?) =
                TimesDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_RESULT, resultItems)
                    }
                }
    }
}
