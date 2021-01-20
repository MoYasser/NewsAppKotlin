package com.yasser.newsapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yasser.newsapp.Database.DataModel
import com.yasser.newsapp.R
import kotlinx.android.synthetic.main.fragment_headline_card.view.*

class SavedAdapter(private var clickListener: SavedCardClickListener) : RecyclerView.Adapter<SavedAdapter.MyViewHolder>(){

    private var newsList = emptyList<DataModel>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(headline: DataModel, action: SavedCardClickListener){
            itemView.setOnClickListener{
                action.onCardClick(headline, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.savedcard,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentItem = newsList[position]
        holder.itemView.headlineText.text = currentItem.title
        holder.itemView.headlinePH.setImageResource(R.drawable.ic_nopic)
        holder.onBind(currentItem, clickListener)


    }

    override fun getItemCount(): Int {
       return newsList.size
    }

    fun setData(news: List<DataModel>){

        this.newsList =  news
        notifyDataSetChanged()
    }

}

interface SavedCardClickListener{
    fun onCardClick(card: DataModel, position: Int)
}