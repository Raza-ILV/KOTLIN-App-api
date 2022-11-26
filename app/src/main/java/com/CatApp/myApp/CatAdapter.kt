package com.myapp.catapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.retrofitcrashcourse.databinding.KittiItemBinding

class CatAdapter : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    inner class CatViewHolder(val binding: KittiItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var cats: List<Cat>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount() = cats.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(KittiItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.binding.apply {
            val cat = cats[position]
            name.text = cat.name
            og.text = cat.country_code
            country.text = cat.origin
            temperament.text = cat.temperament
        }
    }
}