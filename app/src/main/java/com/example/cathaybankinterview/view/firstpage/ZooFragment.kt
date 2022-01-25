package com.example.cathaybankinterview.view.firstpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cathaybankinterview.databinding.FragmentZooBinding
import com.example.cathaybankinterview.model.ApiManager
import com.example.cathaybankinterview.view.firstpage.model.ZooRepository
import com.example.cathaybankinterview.view.firstpage.present.ZooContract
import com.example.cathaybankinterview.view.firstpage.present.ZooPresent
import com.example.cathaybankinterview.view.mainpage.IGoToZooDetail
import com.example.cathaybankinterview.view.secondpage.ZooDetailFragment
import com.example.cathaybankinterview.view.thirdpage.PlantDetail
import com.google.android.material.snackbar.Snackbar

class ZooFragment : Fragment(), ZooContract.ViewContract {

    companion object{
        fun newInstance(): ZooFragment {
            val args = Bundle()

            val fragment = ZooFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentZooBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAdapter: VenueListAdapter

    private lateinit var present : ZooContract.IZooPresent

    private var goToZooDetailListener : IGoToZooDetail? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZooBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        present = ZooPresent(this, ZooRepository(ApiManager.openDataService))
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        present.reloadList()
    }

    private fun initRecyclerView() {
        listAdapter = VenueListAdapter(object : VenueItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                goToZooDetailListener?.goToZooDetailPage(present.viewDetail(position))
            }
        })
        val layoutManager = LinearLayoutManager(requireContext())
        binding.zooIntroductionRecyclerView.layoutManager = layoutManager
        binding.zooIntroductionRecyclerView.addOnScrollListener(object : EndlessScrollListener(layoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                present.loadMoreList()
            }

        })
        binding.zooIntroductionRecyclerView.adapter = listAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun updateList(newList: List<Venue>) {
        listAdapter.submitList(newList)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(binding.zooIntroductionSpace,message,Snackbar.LENGTH_SHORT).show()
    }

    override fun xxxx(siteName: String): String {
        return siteName
    }

    fun setGoToZooDetailListener(listener : IGoToZooDetail){
        goToZooDetailListener = listener
    }
}