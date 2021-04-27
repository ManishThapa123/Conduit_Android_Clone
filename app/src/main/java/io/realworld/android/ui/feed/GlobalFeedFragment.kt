package io.realworld.android.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realworld.android.R

class GlobalFeedFragment : Fragment() {

private lateinit var feedViewModel: FeedViewModel
private lateinit var feedAdapter: ArticleFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        feedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedAdapter = ArticleFeedAdapter()
        val root = inflater.inflate(R.layout.fragment_feed,container,false)
                val binding = root.findViewById<RecyclerView>(R.id.feedRecyclerView)
        binding.layoutManager = LinearLayoutManager(context)
        binding.adapter = feedAdapter
        feedViewModel.fetchGlobalFeed()

        feedViewModel.feed.observe({lifecycle}){
            feedAdapter.submitList(it)
        }

        return root

    }

    override fun onDestroy() {
        super.onDestroy()

    }
}