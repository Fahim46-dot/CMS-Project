package com.example.todo.app.ui.comment.commentList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.app.model.commentDataItem
import com.example.todo.databinding.FragmentCommentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class commentListFragment : Fragment(R.layout.fragment_comment_list) {
    private lateinit var binding: FragmentCommentListBinding
    private val args: commentListFragmentArgs by navArgs()
    private val viewModel: commentListViewModel by viewModels()
    private lateinit var adapter: commentListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()

        adapter = commentListAdapter { comment ->
            adapterOnClick(comment)
        }
    }

    private fun adapterOnClick(comment: commentDataItem) {
        val action = commentListFragmentDirections.actionCommentListFragmentToCommentDetailsFragment(comment.id,args.postId)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCommentListBinding.bind(view)

        initViews()
        initListeners()
        dataLoad()
    }

    private fun initObservers() {
        viewModel.comment.observe(this){
            adapter.submitList(it!!)
        }
    }

    private fun initViews() {
        val layoutManager = LinearLayoutManager(activity)
        binding.commentList.layoutManager = layoutManager
        binding.commentList.adapter = adapter
    }

    private fun initListeners() {
        binding.addBtn.setOnClickListener {
            val action = commentListFragmentDirections.actionCommentListFragmentToCommentAddFragment(args.postId)
            findNavController().navigate(action)
        }
    }

    private fun dataLoad() {
        viewModel.getComment(args.postId, 1)
    }
}
