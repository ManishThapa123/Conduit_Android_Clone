package io.realworld.android.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.realworld.android.data.ArticlesRepo
import io.realworld.api.models.entities.Article
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FeedViewModel: ViewModel() {
    private val _feed = MutableLiveData<List<Article>>()

    val feed : LiveData<List<Article>> = _feed

    fun fetchGlobalFeed() = viewModelScope.launch {
       ArticlesRepo.getGlobalFeed().body()?.let {
           _feed.postValue(it.articles)
           Log.d("FEED","Feed Fetched ${it.articlesCount}")
       }
    }

//    fun `GET articles`(){
//        runBlocking {
//            val articles = conduitClient.publicApi.getArticles()
//            assertNotNull(articles.body()?.articles)
//        }
//    }
//    fun updateFeed(){
////        ArticlesRepo.getGlobalFeed()
//        //to call suspend function
//
//    }

}