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
import com.yasser.newsapp.Adapters.HeadlinesViewAdapter
import com.yasser.newsapp.Adapters.SavedAdapter
import com.yasser.newsapp.Adapters.SavedCardClickListener
import com.yasser.newsapp.Database.DataModel
import com.yasser.newsapp.Database.ViewModel
import com.yasser.newsapp.R
import com.yasser.newsapp.SharedViewModel
import kotlinx.android.synthetic.main.fragment_saved_items.view.*

class SavedItemsFragment : Fragment(), SavedCardClickListener {
    lateinit var viewAdapter: HeadlinesViewAdapter
    private lateinit var newsViewModel: ViewModel
    private lateinit var sharedVM: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_saved_items, container, false)

        val recyclerView = view.RecyclerView
        val adapter = SavedAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        newsViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        newsViewModel.readAllData.observe(viewLifecycleOwner, Observer { news ->
            adapter.setData(news)
        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedVM = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedVM.getSaved().observe(viewLifecycleOwner, Observer { })
    }


    override fun onCardClick(card: DataModel, position: Int) {
        println("Headline clicked from saved")
        findNavController().navigate(R.id.action_menuSaved_to_itemDetailsFragment)
        sharedVM.clickedSaved(card)
    }

}
