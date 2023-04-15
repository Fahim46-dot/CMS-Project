package com.example.todo.app.ui.post.postLIst

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.app.model.postDataItemItem
import com.example.todo.databinding.PostlayoutBinding

class postListAdapter(
    private val onClick: (postDataItemItem) -> Unit
) : ListAdapter<postDataItemItem, postListAdapter.viewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder.from(parent, onClick)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class viewHolder private constructor(
        private val binding: PostlayoutBinding,
        private val onClick: (postDataItemItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: postDataItemItem) {
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body

            binding.root.setOnClickListener {
                onClick(post)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onclick: (postDataItemItem) -> Unit

            ): viewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostlayoutBinding.inflate(layoutInflater, parent, false)
                return postListAdapter.viewHolder(binding, onclick)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<postDataItemItem>() {
            override fun areItemsTheSame(
                oldItem: postDataItemItem,
                newItem: postDataItemItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: postDataItemItem,
                newItem: postDataItemItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
