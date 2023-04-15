package com.example.todo.app.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.R
import com.example.todo.databinding.FragmentUserDetailsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {
    private val args: UserDetailsFragmentArgs by navArgs()

    private lateinit var binding: FragmentUserDetailsBinding

    private val viewModel: UserDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.userdetails.observe(this) { user ->
            user?.let {
                binding.userId.text = "Id: ${user.id}"
                binding.userName.text = "Name: ${user.name}"
                binding.userEmail.text = "Email: ${user.email}"
                binding.userGender.text = "Gender: ${user.gender}"
                binding.userStatus.text = "Status: ${user.status}"
            }
        }
        viewModel.eventDeleteSuccess.observe(this){
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentUserDetailsBinding.bind(view)
        initListeners()
        getDetails()
    }

    private fun initListeners() {
        binding.editButton.setOnClickListener {
            val action =
                UserDetailsFragmentDirections.actionUserDetailsFragmentToUserEditFragment(
                    args.userId
                )
            findNavController().navigate(action)
        }
        binding.deleteButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Are you sure you want to delete this user?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.delete(args.userId)
                }
                .show()
        }
        binding.postButton.setOnClickListener {
            val action =
                UserDetailsFragmentDirections.actionUserDetailsFragmentToPostListFragment(
                    args.userId
                )
            findNavController().navigate(action)
        }
    }

    private fun getDetails() {
        viewModel.getDetails(args.userId)
    }
}
