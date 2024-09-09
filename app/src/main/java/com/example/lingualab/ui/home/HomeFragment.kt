package com.example.lingualab.ui.home

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lingualab.R
import com.example.lingualab.databinding.FragmentHomeBinding
import com.example.lingualab.ui.home.adapter.WordListRecyclerViewAdapter

class HomeFragment : Fragment() {


    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvWordList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWordList.adapter = WordListRecyclerViewAdapter(viewModel.wordList)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}