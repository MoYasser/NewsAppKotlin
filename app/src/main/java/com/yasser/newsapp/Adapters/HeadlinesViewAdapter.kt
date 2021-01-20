package com.yasser.newsapp.Adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yasser.newsapp.NewsModel
import com.yasser.newsapp.R

import kotlinx.android.synthetic.main.fragment_headline_card.view.*

var headlineNumber = 0

class HeadlinesViewAdapter(
    private var headLinesList: MutableList<NewsModel>?,
    private var clickListener: CardClickListener
) : RecyclerView.Adapter<HeadlinesViewAdapter.HeadLineHolder>() {


    class HeadLineHolder(headlineCard: View) : RecyclerView.ViewHolder(headlineCard) {

        @SuppressLint("SetTextI18n")
        fun onBind(headline: NewsModel, action: CardClickListener) {

            itemView.headlineText.text = headline.headLineTitle.split(" - ")[0]
            Glide.with(itemView)
                .load(headline.headLineThumbNail)
                .error(R.drawable.ic_nopic)
                .into(itemView.headlinePH)


            itemView.setOnClickListener {
                action.onCardClick(headline, adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadLineHolder =
        HeadLineHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_headline_card,
                parent,
                false
            )
        )

    override fun getItemCount() = headLinesList!!.size

    override fun onBindViewHolder(holder: HeadLineHolder, position: Int) {
        val headline = headLinesList!![position]
        headlineNumber = position + 1
        holder.onBind(headline, clickListener)
    }

    fun nextPage(headlines: ArrayList<NewsModel>) {
        this.headLinesList?.addAll(headlines)
        notifyItemRangeChanged(this.headLinesList!!.size, (headLinesList!!.size) - 1)
    }

}

interface CardClickListener {
    fun onCardClick(card: NewsModel, position: Int)
}
