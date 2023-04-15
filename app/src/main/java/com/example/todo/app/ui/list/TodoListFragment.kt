package com.example.todo.app.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.app.model.userDataItem
import com.example.todo.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : Fragment(R.layout.fragment_user_list) {
    private lateinit var binding: FragmentUserListBinding

    private val viewModel: TodoListViewModel by viewModels()

    private lateinit var adapter: TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()

        // adapter is not called
        adapter = TodoListAdapter { user ->
            adapterOnClick(user)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentUserListBinding.bind(view)

        initViews()
        initListeners()
        load()
    }

    private fun initListeners() {
        binding.addBtn.setOnClickListener {
            // For navigation
            findNavController().navigate(R.id.action_todoListFragment_to_userAddFragment2)
        }
    }

    private fun load() {
        // Called in viewModel for data
        viewModel.getusers()
    }

    private fun initObservers() {
        viewModel.user.observe(this) { users ->
            adapter.submitList(users!!)
        }
    }

    private fun initViews() {
        val layoutManager = LinearLayoutManager(activity)
        binding.userList.layoutManager = layoutManager
        binding.userList.adapter = adapter
    }

    private fun adapterOnClick(user: userDataItem) {
        // Here we can pass data using argument
        val action = TodoListFragmentDirections.actionTodoListFragmentToUserDetailsFragment(user.id)
        findNavController().navigate(action)
    }
}
