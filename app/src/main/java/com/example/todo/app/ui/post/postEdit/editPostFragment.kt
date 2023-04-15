package com.example.todo.app.ui.post.postEdit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.R
import com.example.todo.databinding.FragmentPostEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class editPostFragment : Fragment(R.layout.fragment_post_edit) {
    private lateinit var binding: FragmentPostEditBinding

    private val viewModel: postEditViewModel by viewModels()

    private val args: editPostFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        // When livedata is changed we observe here
        viewModel.updatePost.observe(this) { post ->
            post?.let {
                binding.etTitle.setText(post.title)
                binding.etBody.setText(post.body)
            }
        }
        viewModel.updateSuccess.observe(this) {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPostEditBinding.bind(view)

        initListeners()
        getUpdate()
    }

    private fun getUpdate() {
        // First of all we collected the existing data
        viewModel.getDetails(args.postId)
    }

    private fun initListeners() {
        binding.btnUpdate.setOnClickListener {
            viewModel.updatepost(
                args.userId,
                args.postId,
                binding.etBody.text.toString(),
                binding.etTitle.text.toString()
            )
        }
    }
}
