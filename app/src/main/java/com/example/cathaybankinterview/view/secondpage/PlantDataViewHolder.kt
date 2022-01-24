package com.example.cathaybankinterview.view.secondpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cathaybankinterview.R
import com.example.cathaybankinterview.databinding.LayoutPlantDataViewHolderBinding
import com.example.cathaybankinterview.extention.loadImageUrl
import com.example.cathaybankinterview.view.thirdpage.PlantDetail

class PlantDataViewHolder private constructor(private val binding: LayoutPlantDataViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun createViewHolder(viewGroup: ViewGroup): PlantDataViewHolder {
            return PlantDataViewHolder(
                LayoutPlantDataViewHolderBinding.inflate(
                    LayoutInflater.from(
                        viewGroup.context
                    )
                )
            )
        }
    }

    private var clickListener : PlantDataItemClickListener? = null

    init {
        binding.root.setOnClickListener {
            clickListener?.onItemClick(adapterPosition,it)
        }
    }

    /**
     * 綁定資料
     */
    fun bindData(data: PlantDetail,itemClickListener: PlantDataItemClickListener) {
        clickListener = itemClickListener
        binding.plantDataAlisaTextView.text = data.alias
        binding.plantDataNameTextView.text = data.chineseName
        binding.plantDataPicImageView.loadImageUrl(data.pictureUrl)
    }
}