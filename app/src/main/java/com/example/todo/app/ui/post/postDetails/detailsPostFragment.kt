package com.example.todo.app.ui.post.postDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.R
import com.example.todo.databinding.FragmentPostDetailsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class detailsPostFragment : Fragment(R.layout.fragment_post_details) {
    private lateinit var binding: FragmentPostDetailsBinding
    private val viewModel: postDetailsViewModel by viewModels()

    private val args: detailsPostFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPostDetailsBinding.bind(view)

        initListeners()

        getDetails()
    }

    fun initObservers() {
        viewModel.eventDeleteSuccess.observe(this) { isSuccess ->
           if (isSuccess == true) {
                findNavController().popBackStack()
           }
        }
        viewModel.postDetails.observe(this) { post ->
            post?.let {
                binding.tvTitle.text = post.title
                binding.tvBody.text = post.body
            }
        }
    }

    fun initListeners() {
        binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Are you sure you want to delete this Post?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.deletePost(args.postId)
                }
                .show()
        }

        binding.btnEdit.setOnClickListener {
            val action = detailsPostFragmentDirections.actionDetailsPostFragment2ToEditPostFragment2(args.userId,
            args.postId)
            findNavController().navigate(action)
        }

        binding.btnComments.setOnClickListener {
            val action = detailsPostFragmentDirections.actionDetailsPostFragment2ToCommentListFragment(args.postId)
            findNavController().navigate(action)
        }
    }

    private fun getDetails() {
        viewModel.getDetails(args.postId)
    }
}