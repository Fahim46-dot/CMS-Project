package com.example.todo.app.ui.comment.commentList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.app.model.commentDataItem
import com.example.todo.databinding.CustomCommentLayoutBinding

class commentListAdapter(
    private val onClick: (commentDataItem) -> Unit
) : ListAdapter<commentDataItem, commentListAdapter.viewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder.from(parent, onClick)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val comment = getItem(position)
        holder.bind(comment)
    }

    class viewHolder private constructor(
        private val binding: CustomCommentLayoutBinding,
        private val onClick: (commentDataItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: commentDataItem) {
            binding.tvBody.text = item.body
            binding.tvName.text = item.name
            binding.tvEmail.text = item.email

            binding.root.setOnClickListener {
                onClick(item)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onclick: (commentDataItem) -> Unit

            ): commentListAdapter.viewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CustomCommentLayoutBinding.inflate(layoutInflater, parent, false)
                return commentListAdapter.viewHolder(binding, onclick)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<commentDataItem>() {
            override fun areItemsTheSame(
                oldItem: commentDataItem,
                newItem: commentDataItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: commentDataItem,
                newItem: commentDataItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
