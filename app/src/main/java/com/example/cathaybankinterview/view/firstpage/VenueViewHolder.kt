package com.example.cathaybankinterview.view.firstpage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cathaybankinterview.R
import com.example.cathaybankinterview.databinding.LayoutVenueViewHolderBinding
import com.example.cathaybankinterview.extention.loadImageUrl

class VenueViewHolder(private val binding: LayoutVenueViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): VenueViewHolder {
            return VenueViewHolder(LayoutVenueViewHolderBinding.inflate(LayoutInflater.from(parent.context)))
        }
    }

    private var clickListener : VenueItemClickListener? = null

    init {
        binding.root.setOnClickListener {
            clickListener?.onItemClick(adapterPosition,it)
        }
    }

    fun bindData(data : Venue,listener: VenueItemClickListener){
        clickListener = listener
        with(binding){
            val context = root.context
            venueNameTextView.text = data.name
            venueTimeTextView.text = getDisplayMemo(context,data.memo)
            venueSimpleIntroductTextView.text = data.info
            venuePicImageView.loadImageUrl(data.picUrl)
        }
    }

    private fun getDisplayMemo(context : Context, memo : String) : String{
        return if (memo == ""){
            context.getString(R.string.default_memo)
        } else {
            memo
        }
    }
}