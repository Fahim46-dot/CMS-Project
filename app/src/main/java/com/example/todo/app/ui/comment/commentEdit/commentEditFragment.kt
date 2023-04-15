package com.example.todo.app.ui.comment.commentEdit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.R
import com.example.todo.databinding.FragmentCommentEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class commentEditFragment : Fragment(R.layout.fragment_comment_edit) {
    private lateinit var binding: FragmentCommentEditBinding
    private val args: commentEditFragmentArgs by navArgs()
    private val viewModel: commentEditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.updateComment.observe(this) {
            it?.let {
                binding.etName.setText(it.name)
                binding.etEmail.setText(it.email)
                binding.etBody.setText(it.body)
            }
        }
        viewModel.updateSuccess.observe(this) {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCommentEditBinding.bind(view)

        initListeners()
        load()
    }

    private fun load() {
        viewModel.getDetails(args.commentId)
    }

    private fun initListeners() {
        binding.btnUpdate.setOnClickListener {
            viewModel.updateComment(
                args.commentId,
                binding.etName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etBody.text.toString(),
                args.postId
            )
        }
    }
}
