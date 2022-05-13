package com.example.yournews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yournews.model.Article
import com.bumptech.glide.Glide
import com.example.yournews.R
import com.example.yournews.databinding.ItemArticlePreviewBinding


class NewsAdapter :RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
       val article =differ.currentList[position]
        holder.binding.apply {
            Glide.with(ivArticleImage).load(article.urlToImage).into(ivArticleImage)
            tvSource.text=article.source.name
            tvTitle.text=article.title
            tvDescription.text=article.description
            tvPublishedAt.text=article.publishedAt
            root.setOnClickListener {
                onItemClickListner?.let { it(article) }
            }

        }
    }

    private var onItemClickListner:((Article) -> Unit)?=null
    fun setOnItemClickListner(listner:(Article)-> Unit){ onItemClickListner = listner}

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    inner class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding = ItemArticlePreviewBinding.bind(itemView)
    }

    private val differCallback = object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem==newItem
        }
    }
    val differ=AsyncListDiffer(this,differCallback)

}