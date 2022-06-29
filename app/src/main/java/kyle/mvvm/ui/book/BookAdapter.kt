package kyle.mvvm.ui.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kyle.mvvm.databinding.ItemBookBinding
import kyle.mvvm.net.res.BookInfo

/**
 * Copyright (C) 2022 Kakao corp. All rights reserved.
 *
 */

class BookAdapter : ListAdapter<BookInfo, RecyclerView.ViewHolder>(BookDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookViewHolder(
            binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookViewHolder).bind(getItem(position))
    }

    class BookViewHolder(
        val binding: ItemBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookInfo) {
            binding.apply {
                // thumbnail
                Glide.with(thumbnail).load(book.image).into(thumbnail)

                // title
                title.text = book.title

                // subTitle
                subtitle.text = book.subtitle

                // price
                price.text = book.price
            }
        }
    }
}

private class BookDiffCallback : DiffUtil.ItemCallback<BookInfo>() {

    override fun areItemsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean {
        return oldItem == newItem
    }
}