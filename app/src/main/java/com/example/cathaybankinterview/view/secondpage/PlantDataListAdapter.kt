package com.example.cathaybankinterview.view.secondpage

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.cathaybankinterview.view.thirdpage.PlantDetail

class PlantDataListAdapter(private val plantDataItemClickListener: PlantDataItemClickListener) : ListAdapter<PlantDetail,PlantDataViewHolder>(object : DiffUtil.ItemCallback<PlantDetail>(){
    override fun areItemsTheSame(oldItem: PlantDetail, newItem: PlantDetail): Boolean {
        return oldItem.chineseName == newItem.chineseName
    }

    override fun areContentsTheSame(oldItem: PlantDetail, newItem: PlantDetail): Boolean {
        return oldItem == newItem
    }

}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantDataViewHolder {
        return PlantDataViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PlantDataViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.bindData(this,plantDataItemClickListener)
        }
    }
}