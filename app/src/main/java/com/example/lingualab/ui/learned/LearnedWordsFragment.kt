package com.example.lingualab.ui.learned

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lingualab.databinding.FragmentLearnedWordsBinding
import com.example.lingualab.ui.learned.adapter.LearnedWordListRecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnedWordsFragment : Fragment() {




    private lateinit var _binding: FragmentLearnedWordsBinding
    private val binding get() = _binding

    private val viewModel: LearnedWordsViewModel by viewModels()
    private lateinit var viewAdapter: LearnedWordListRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLearnedWordsBinding.inflate(inflater, container, false)
        binding.rvLearnedWordList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.checkWordList()
        viewModel.learnedWords.observe(viewLifecycleOwner) {
            viewAdapter = LearnedWordListRecyclerView(it) { word ->
                viewModel.deleteWord(word)
            }
            binding.rvLearnedWordList.adapter = viewAdapter
        }


        return binding.root

    }
}