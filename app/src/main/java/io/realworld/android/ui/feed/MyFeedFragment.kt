package io.realworld.android.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realworld.android.R

class MyFeedFragment: Fragment() {

    private lateinit var feedViewModel: FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter
    private var binding: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        feedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedAdapter = ArticleFeedAdapter { openArticle(it) }
        val root = inflater.inflate(R.layout.fragment_feed,container,false)
        binding = root.findViewById(R.id.feedRecyclerView)
        binding?.layoutManager = LinearLayoutManager(context)
        binding?.adapter = feedAdapter
        return root

    }

    fun openArticle(articleId: String){
        findNavController().navigate(R.id.action_myFeed_action_openArticle,
            bundleOf(
                resources.getString(R.string.article_id) to articleId)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedViewModel.fetchMyFeed()

        feedViewModel.feed.observe({lifecycle}){
            feedAdapter.submitList(it)
        }
    }

}