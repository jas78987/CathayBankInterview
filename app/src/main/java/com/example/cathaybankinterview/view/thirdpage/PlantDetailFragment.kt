package com.example.cathaybankinterview.view.thirdpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cathaybankinterview.R
import com.example.cathaybankinterview.databinding.FragmentPlanDetailBinding
import com.example.cathaybankinterview.extention.loadImageUrl
import com.example.cathaybankinterview.view.thirdpage.present.PlantDetailContract
import com.example.cathaybankinterview.view.thirdpage.present.PlantDetailPresent

class PlantDetailFragment : Fragment(),PlantDetailContract.ViewContract{

    companion object{

        private const val BUNDLE_KEY_PLANT_DETAIL = "BUNDLE_KEY_PLANT_DETAIL"

        fun newInstance(plantDetail: PlantDetail): PlantDetailFragment {
            val args = Bundle()
            args.putSerializable(BUNDLE_KEY_PLANT_DETAIL,plantDetail)
            val fragment = PlantDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentPlanDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var present : PlantDetailContract.IPlantDetailPresent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        present = PlantDetailPresent(this)
        val plantDetail = arguments?.getSerializable(BUNDLE_KEY_PLANT_DETAIL) as? PlantDetail
        present.loadFromArguments(plantDetail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setPlantDetail(plantDetail: PlantDetail) {
        with(binding){
            plantPictureImageView.loadImageUrl(plantDetail.pictureUrl)
            plantChineseNameTextView.text = plantDetail.chineseName
            plantDetailLatinNameTextView.text = plantDetail.englishName
            plantDetailAliasTextView.text = plantDetail.alias
            plantDetailIntroductionTextView.text = plantDetail.brief
            plantDetailDistinguishTextView.text = plantDetail.feature
            plantDetailFunctionTextView.text = plantDetail.functionAndApplication
            plantDetailLastUpdateTextView.text = getString(R.string.last_update_template,plantDetail.updateTime)
        }
    }


}