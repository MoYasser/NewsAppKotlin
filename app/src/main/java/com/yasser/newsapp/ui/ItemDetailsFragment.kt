package com.yasser.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

import com.yasser.newsapp.Database.DataModel
import com.yasser.newsapp.Database.ViewModel
import com.yasser.newsapp.NewsModel
import com.yasser.newsapp.R
import com.yasser.newsapp.SharedViewModel
import kotlinx.android.synthetic.main.fragment_item_details.*
import kotlinx.android.synthetic.main.fragment_item_details.view.*

class ItemDetailsFragment : Fragment() {
    private lateinit var sharedVM: SharedViewModel
    private lateinit var newsViewModel: ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{ val view = inflater.inflate(R.layout.fragment_item_details, container, false)


    newsViewModel = ViewModelProvider(this).get(ViewModel::class.java)

    view.saveNews.setOnClickListener{
        insertDB()
    }
    view.backButton.setOnClickListener{
        findNavController().navigate(R.id.action_itemDetailsFragment_to_menuHeadlines)
    }

    return view
}
private fun insertDB(){

    val title = newsTitle.text.toString()
    val description = newsDescription.text.toString()
    val content = newsContent.text.toString()


   val news = DataModel(0,title, description,"null ",content,"null")

    newsViewModel.saveNews(news)

    Toast.makeText(requireContext(),"SAVED", Toast.LENGTH_LONG).show()


}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedVM = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedVM.getHeadline().observe(viewLifecycleOwner, Observer { t -> bind(t)  })
        sharedVM.getSaved().observe(viewLifecycleOwner, Observer { t -> bind(t) })

        }

    private fun bind(card: DataModel){
        newsTitle.text = card.title
        newsDescription.text = card.description
        newsContent.text = card.content
    }


    private fun bind(card: NewsModel){
        Glide.with(this).load(card.headLineThumbNail).error(R.drawable.ic_nopic).into(itemPic)
        newsTitle.text = card.headLineTitle
        newsDescription.text = card.newsDescription
        newsContent.text = card.newsContent
    }



}
