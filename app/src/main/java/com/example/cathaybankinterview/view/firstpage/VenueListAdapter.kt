package com.example.cathaybankinterview.view.firstpage

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class VenueListAdapter(private val itemClickListener: VenueItemClickListener) : ListAdapter<Venue, VenueViewHolder>(object : DiffUtil.ItemCallback<Venue>(){
    override fun areItemsTheSame(oldItem: Venue, newItem: Venue): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Venue, newItem: Venue): Boolean {
        return oldItem == newItem
    }

}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        return VenueViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.bindData(this,itemClickListener)
        }
    }
}