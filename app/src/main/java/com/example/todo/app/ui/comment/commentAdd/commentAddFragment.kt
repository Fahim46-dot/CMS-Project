package com.example.todo.app.ui.comment.commentAdd

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.R
import com.example.todo.databinding.FragmentCommentAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class commentAddFragment : Fragment(R.layout.fragment_comment_add) {
    private lateinit var binding: FragmentCommentAddBinding
    private val args: commentAddFragmentArgs by navArgs()
    private val viewModel: commentAddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.comment.observe(this) {
            if (it == true) {
                findNavController().popBackStack()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCommentAddBinding.bind(view)

        initListeners()
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.addComment(
                args.postId,
                binding.etName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etBody.text.toString()
            )
        }
    }
}
