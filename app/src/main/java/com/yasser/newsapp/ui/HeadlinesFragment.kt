package com.yasser.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yasser.newsapp.Adapters.CardClickListener
import com.yasser.newsapp.Adapters.HeadlinesViewAdapter
import com.yasser.newsapp.NewsModel
import com.yasser.newsapp.R
import com.yasser.newsapp.SharedViewModel

import kotlinx.android.synthetic.main.fragment_headlines.*

class HeadlinesFragment : Fragment(), CardClickListener {
    lateinit var sharedVM: SharedViewModel
    var currentPage: Int = 1
    private lateinit var viewAdapter: HeadlinesViewAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedVM = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        currentPage = 1
        return inflater.inflate(R.layout.fragment_headlines, container, false)}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAdapter = HeadlinesViewAdapter(mutableListOf(), this)
        linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        headlines_view.adapter = viewAdapter
        currentPage = 1
        headlines_view.layoutManager = linearLayoutManager

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        currentPage = 1
        sharedVM.getHeadlines().observe(viewLifecycleOwner, Observer { t -> viewHeadlines(t); printTitles(t) })
    }


    override fun onCardClick(card: NewsModel, position: Int) {
        findNavController().navigate(R.id.action_menuHeadlines_to_itemDetailsFragment)
        sharedVM.clickedHeadline(card)
    }

    private fun viewHeadlines(headlines: ArrayList<NewsModel>){
        viewAdapter.nextPage(headlines)
        attachOnScrollListener()
    }


    private fun printTitles(list: ArrayList<NewsModel>){
        for (i in 0 until list.size){
            println(">$i = ${list[i].headLineTitle}")
        }
    }

    private fun attachOnScrollListener(){
        headlines_view.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItems = linearLayoutManager.itemCount
                val visibleItemsCount = linearLayoutManager.childCount
                val firstVisibleItem = linearLayoutManager.findLastVisibleItemPosition()

                if(firstVisibleItem + visibleItemsCount >= totalItems/2) {
                    headlines_view.removeOnScrollListener(this)
                    if(sharedVM.getHeadlines().value?.size != 0){
                        currentPage++
                        sharedVM.getArticles(currentPage)
                    }

                }
            }
        })
    }

}





