package com.example.todo.app.ui.post.postAdd

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.R
import com.example.todo.databinding.FragmentPostAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class postAddFragment : Fragment(R.layout.fragment_post_add) {
    private lateinit var binding: FragmentPostAddBinding
    private val viewModel: postAddViewModel by viewModels()
    private val args: postAddFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPostAddBinding.bind(view)

        initListeners()
    }

    private fun initObservers() {
        viewModel.addPost.observe(this) {
            findNavController().popBackStack()
        }
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.addpost(
                args.userId,
                binding.etTitle.text.toString(),
                binding.etBody.text.toString()
            )
        }
    }
}
