package com.example.todo.app.ui.add

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentUserAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserAddFragment : Fragment(R.layout.fragment_user_add) {
    private lateinit var binding: FragmentUserAddBinding
    private val viewModel: UserAddFragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentUserAddBinding.bind(view)

        initView()
        initListeners()
    }

    private fun initObservers() {
        viewModel.addUserSuccess.observe(this) {
            findNavController().popBackStack()
        }
    }

    private fun initView() {
        val genderItems = listOf("Male", "Female")
        val genderAdapter =
            ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                genderItems
            )
        binding.tvGender.setAdapter(genderAdapter)

        val statusItems = listOf("Active", "Inactive")
        val statusAdapter =
            ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                statusItems
            )
        binding.tvStatus.setAdapter(statusAdapter)
    }

    private fun initListeners() {
        binding.addBtn.setOnClickListener {
            viewModel.addUser(
                binding.etName.text.toString(),
                binding.etEmail.text.toString(),
                binding.tvGender.text.toString(),
                binding.tvStatus.text.toString()
            )
        }
    }
}
