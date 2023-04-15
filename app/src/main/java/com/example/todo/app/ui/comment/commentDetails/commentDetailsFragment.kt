package com.example.todo.app.ui.comment.commentDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.R
import com.example.todo.databinding.FragmentCommentDetailsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class commentDetailsFragment : Fragment(R.layout.fragment_comment_details) {
    private lateinit var binding: FragmentCommentDetailsBinding
    private val args: commentDetailsFragmentArgs by navArgs()
    private val viewModel: commentDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.commentDetails.observe(this) { comment ->
            comment?.let {
                binding.tvName.text = comment.name
                binding.tvEmail.text = comment.email
                binding.tvBody.text = comment.body
            }
        }
        viewModel.delete.observe(this) {
            if (it == true) {
                findNavController().popBackStack()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCommentDetailsBinding.bind(view)

        initListners()
        loadData()
    }

    private fun loadData() {
        viewModel.getComments(args.commentId)
    }

    private fun initListners() {
        binding.btnEdit.setOnClickListener {
            val action =
                commentDetailsFragmentDirections.actionCommentDetailsFragmentToCommentEditFragment(
                    args.postId,
                    args.commentId
                )
            findNavController().navigate(action)
        }
        binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Are you sure you want to delete this user?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.delete(args.commentId)
                }
                .show()
        }
    }
}
