package com.example.todo.app.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.app.model.userDataItem
import com.example.todo.databinding.UserdatashowBinding

class TodoListAdapter(
    private val onclick: (userDataItem) -> Unit
) :
    ListAdapter<userDataItem, TodoListAdapter.TodoViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder.from(parent, onclick)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
    }

    class TodoViewHolder private constructor(
        private val binding: UserdatashowBinding,
        private val onclick: (userDataItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: userDataItem) {
            binding.userName.text = "Name: ${item.name}"
            binding.userEmail.text = "Email: ${item.email}"
            binding.userGender.text = "Gender: ${item.gender}"
            binding.userStatus.text = "Status: ${item.status}"
            binding.userId.text = "Id: ${item.id}"

            binding.root.setOnClickListener {
                onclick(item)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onclick: (userDataItem) -> Unit

            ): TodoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserdatashowBinding.inflate(layoutInflater, parent, false)
                return TodoViewHolder(binding, onclick)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<userDataItem>() {
            override fun areItemsTheSame(oldItem: userDataItem, newItem: userDataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: userDataItem, newItem: userDataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
