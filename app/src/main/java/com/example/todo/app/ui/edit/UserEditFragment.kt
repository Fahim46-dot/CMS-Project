package com.example.todo.app.ui.edit

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.R
import com.example.todo.databinding.FragmentUserEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserEditFragment : Fragment(R.layout.fragment_user_edit) {

    private lateinit var binding: FragmentUserEditBinding
    private val args: UserEditFragmentArgs by navArgs()
    private val viewModel: UserEditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentUserEditBinding.bind(view)
        initViews()
        initListeners()
    }
    private fun initObserver() {
        viewModel.updateuser.observe(this) {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {
        val gender = listOf("Male", "Female")

        val genderAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            gender
        )

        binding.tvGender.setAdapter(genderAdapter)

        val status = listOf("Active", "Inactive")

        val statusAdapter = ArrayAdapter(
            requireContext(),
            androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item,
            status
        )

        binding.tvStatus.setAdapter(statusAdapter)
    }

    private fun initListeners() {
        binding.updateBtn.setOnClickListener {
            viewModel.update(
                args.userId,
                binding.etName.text.toString(),
                binding.etEmail.text.toString(),
                binding.tvGender.text.toString(),
                binding.tvStatus.text.toString()
            )
        }
    }
}
