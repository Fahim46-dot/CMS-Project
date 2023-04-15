package com.example.todo.app.ui.post.postLIst

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.app.model.postDataItemItem
import com.example.todo.databinding.FragmentPostListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class postListFragment : Fragment(R.layout.fragment_post_list) {

    private lateinit var binding: FragmentPostListBinding
    private val viewModel: postListViewModel by viewModels()
    private val args: postListFragmentArgs by navArgs()
    private lateinit var adapter: postListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()

        adapter = postListAdapter { post ->
            adapterOnCLick(post)
        }
    }

    private fun adapterOnCLick(post: postDataItemItem) {
        val action = postListFragmentDirections.actionPostListFragmentToDetailsPostFragment2(args.userId,post.id)
        findNavController().navigate(action)
    }

    private fun initObservers() {
        viewModel.post.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPostListBinding.bind(view)

        initViews()
        initListeners()
        load()
    }

    private fun initViews() {
        val layoutManager = LinearLayoutManager(activity)
        binding.postList.layoutManager = layoutManager
        binding.postList.adapter = adapter
    }

    private fun initListeners() {
        binding.addBtn.setOnClickListener {
            val action = postListFragmentDirections.actionPostListFragmentToPostAddFragment(args.userId)
            findNavController().navigate(action)
        }
    }

    private fun load() {
        viewModel.getPost(args.userId)
    }
}
