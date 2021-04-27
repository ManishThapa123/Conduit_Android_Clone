package io.realworld.android.ui.feed

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.realworld.android.R
import io.realworld.android.databinding.ListItemArticleBinding
import io.realworld.api.models.entities.Article

class ArticleFeedAdapter : ListAdapter<Article, ArticleFeedAdapter.ArticleViewHolder>(
      object : DiffUtil.ItemCallback<Article>(){
          override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
              return oldItem == newItem
          }

          override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
             return oldItem.toString() == newItem.toString()
          }

      }
) {


    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_article, parent, false)

        return ArticleViewHolder(view)

    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        ListItemArticleBinding.bind(holder.itemView).apply {
            val article = getItem(position)

            authorTextView.text = article.author.username
            titleTextView.text = article.title
            bodyTextView.text = article.body
            imageView.setBackgroundColor(Color.GREEN)
            dateTextView.text = "27-April-2021"


        }

    }
}