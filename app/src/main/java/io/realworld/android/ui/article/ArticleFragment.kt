package io.realworld.android.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.realworld.android.R
import io.realworld.android.extensions.loadImage
import io.realworld.android.extensions.showFormattedDate

class ArticleFragment: Fragment() {

    lateinit var articleViewModel: ArticleViewModel
    private var root: View? = null
    private var articleId: String? = null

    private var articleTitle: TextView? = null
    private var authorName: TextView? = null
    private var articleDate: TextView? = null
    private var authorImage: ImageView? = null
    private var body: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        root = inflater.inflate(R.layout.fragment_article,container,false)

        arguments?.let {
            articleId =  it.getString(resources.getString(R.string.article_id))
        }

        articleId?.let {
            articleViewModel.fetchArticle(it)

        }

//        savedInstanceState?.let {
//            articleId = it.getString(resources.getString(R.string.article_id))
//            Toast.makeText(requireContext(),"Opening Article $articleId", Toast.LENGTH_LONG)
//                .show()
//        }
        articleTitle = root?.findViewById(R.id.titleTextView)
        authorName = root?.findViewById(R.id.authorTextView)
        authorImage = root?.findViewById(R.id.avatarImageView)
        articleDate = root?.findViewById(R.id.dateTextView)
        body = root?.findViewById(R.id.bodyTextView)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.article.observe({lifecycle}){

//            root?.findViewById<TextView>(R.id.titleTextView)?.text = it.createdAt
            root?.apply {
                articleTitle?.text = it.title
                authorName?.text = it.author.username
                body?.text = it.body
                articleDate?.showFormattedDate(it.createdAt)
                authorImage?.loadImage(it.author.image,true)


            }



        }


    }
}