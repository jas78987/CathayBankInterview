package com.example.cathaybankinterview.view.secondpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cathaybankinterview.databinding.FragmentZooDetailBinding
import com.example.cathaybankinterview.model.ApiManager
import com.example.cathaybankinterview.view.firstpage.Venue
import com.example.cathaybankinterview.view.mainpage.IGoToPlantDetail
import com.example.cathaybankinterview.view.secondpage.model.ZooDetailRepository
import com.example.cathaybankinterview.view.secondpage.present.ZooDetailContract
import com.example.cathaybankinterview.view.secondpage.present.ZooDetailPresent
import com.example.cathaybankinterview.view.thirdpage.PlantDetail
import android.content.Intent
import android.net.Uri
import android.text.SpannedString
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.widget.Toast
import androidx.core.text.buildSpannedString
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cathaybankinterview.R
import com.example.cathaybankinterview.database.DatabaseManager
import com.example.cathaybankinterview.extention.loadImageUrl


class ZooDetailFragment : Fragment(), ZooDetailContract.ViewContract {

    companion object {

        private const val BUNDLE_VENUE = "BUNDLE_VENUE"

        fun newInstance(venue: Venue): ZooDetailFragment {
            val args = Bundle()
            args.putSerializable(BUNDLE_VENUE, venue)

            val fragment = ZooDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentZooDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var present: ZooDetailContract.IZooDetailPresent

    private var goToPlantDetailListener: IGoToPlantDetail? = null

    private var venueInfo: Venue? = null

    private lateinit var listAdapter: PlantDataListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        venueInfo = arguments?.getSerializable(BUNDLE_VENUE) as? Venue
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZooDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        binding.zooDetailOpenInWebTextView.movementMethod = LinkMovementMethod.getInstance()
        present = ZooDetailPresent(
            this,
            ZooDetailRepository(ApiManager.openDataService, DatabaseManager.getInstance())
        )
        present.loadDetail(venueInfo!!)
    }

    private fun initRecyclerView() {
        listAdapter = PlantDataListAdapter(object : PlantDataItemClickListener {
            override fun onItemClick(position: Int, view: View) {
                goToPlantDetailListener?.goToPlantDetailPage(present.viewPlantDetail(position))
            }

        })
        binding.zooDetailPlantDataListRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        binding.zooDetailPlantDataListRecyclerView.adapter = listAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setGoToPlantDetailListener(listener: IGoToPlantDetail) {
        goToPlantDetailListener = listener
    }

    override fun displayDetail(venue: Venue) {
        with(binding) {
            zooDetailImageView.loadImageUrl(venue.picUrl)
            zooDetailMemoTextView.text = venue.memo
            zooDetailBriefTextView.text = venue.info
            zooDetailCategoryTextView.text = venue.category
            zooDetailOpenInWebTextView.text = buildSpannedString {
                val text = getString(R.string.open_in_web)
                append(text)
                setSpan(
                    URLSpan(venue.webUrl),
                    0,
                    text.length,
                    SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }

    override fun openInWeb(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun updateList(newList: List<PlantDetail>) {
        listAdapter.submitList(newList)
    }

    override fun showErrorMessage(throwable: Throwable) {
        Toast.makeText(requireContext(), "${throwable.message}", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading(enable: Boolean) {
        binding.zooDetailProgressBar.isVisible = enable
    }

}