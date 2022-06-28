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
class BookAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<BookInfo>()

    fun submitList(list: List<BookInfo>) {
        items.apply {
            clear()
            addAll(list)
        }

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookViewHolder(
            binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookViewHolder).bind(items[position])
    }

    class BookViewHolder(
        val binding: ItemBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookInfo) {
            binding.apply {
                // thumbnail
                Glide.with(thumbnail).load(book.image)

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

//class BookAdapter : ListAdapter<BookInfo, RecyclerView.ViewHolder>(BookDiffCallback()) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return BookViewHolder(
//            binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val plant = getItem(position)
//        (holder as BookViewHolder).bind(plant)
//    }
//
//    class BookViewHolder(
//        val binding: ItemBookBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(book: BookInfo) {
//            binding.apply {
//                // thumbnail
//                Glide.with(thumbnail).load(book.image)
//
//                // title
//                title.text = book.title
//
//                // subTitle
//                subtitle.text = book.subtitle
//
//                // price
//                price.text = book.price
//            }
//        }
//    }
//}
//
//private class BookDiffCallback : DiffUtil.ItemCallback<BookInfo>() {
//
//    override fun areItemsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean {
//        return oldItem === newItem
//    }
//
//    override fun areContentsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean {
//        return oldItem == newItem
//    }
//}