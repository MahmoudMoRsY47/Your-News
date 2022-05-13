package com.example.yournews.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yournews.adapters.NewsAdapter
import com.example.yournews.util.Resource
import com.example.yournews.R
import com.example.yournews.databinding.FragmentBreakingNewsBinding
import com.example.yournews.ui.NewsViewModel


class NewsFragment : Fragment() {
    lateinit var binding: FragmentBreakingNewsBinding
    val viewModel: NewsViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_breaking_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.view = viewModel

        setUpRecycle()

        newsAdapter.setOnItemClickListner {
//            val bundle=Bundle().apply {
//                putSerializable("article",it)
//            }
            val action = NewsFragmentDirections.actionNewsFragmentToArticleFragment(it)
            findNavController().navigate(action)
        }

        viewModel.news.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    hideProg()
                    it.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error -> {
                    hideProg()
                    it.message?.let {
                        Log.e("news", "An error ocured: $it")
                    }
                }
                is Resource.Loading -> {
                    showProg()
                }
            }
        })
    }

    private fun hideProg() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProg() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setUpRecycle() {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}