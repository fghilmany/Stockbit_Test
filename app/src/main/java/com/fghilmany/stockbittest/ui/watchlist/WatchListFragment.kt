package com.fghilmany.stockbittest.ui.watchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fghilmany.stockbittest.core.data.Resource
import com.fghilmany.stockbittest.core.watchlist.WatchListAdapter
import com.fghilmany.stockbittest.databinding.FragmentWatchListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchListFragment : Fragment() {

    private var _binding : FragmentWatchListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WatchlistViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()

        binding.swipeRefresh.setOnRefreshListener {
            initRv()
        }

    }

    private fun initRv() {
        val watchListAdapter = WatchListAdapter()
        viewModel.getWatchList.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    binding.swipeRefresh.isRefreshing = true
                }
                is Resource.Success -> {
                    binding.swipeRefresh.isRefreshing = false
                    val data = it.data?.data
                    if (data?.isEmpty() == true){
                        binding.groupEmpty.visibility = View.VISIBLE
                    }else{
                        binding.groupEmpty.visibility = View.GONE
                        watchListAdapter.setList(data)
                        watchListAdapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    binding.swipeRefresh.isRefreshing = false
                    Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.rvWatchlist.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = watchListAdapter
        }
    }

}