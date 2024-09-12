package com.example.lingualab.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lingualab.R
import com.example.lingualab.data.model.Word
import com.example.lingualab.databinding.FragmentHomeBinding
import com.example.lingualab.databinding.PopupBinding
import com.example.lingualab.ui.home.adapter.WordListRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: WordListRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        val recyclerView = binding.rvWordList
        val swipeRefreshLayout = binding.swipeRefresh


        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.wordList.observe(viewLifecycleOwner, Observer { wordList ->
            adapter = WordListRecyclerViewAdapter(wordList, ::showPopup)
            recyclerView.adapter = adapter
        })
        viewModel.checkedWordList()
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.swipeRefresh()
            swipeRefreshLayout.isRefreshing = false
        }
        return view
    }

    private fun clickWord(word: Word) {
        viewModel.saveAndRemoveWord(word)
    }


    private fun showPopup(word: Word) {
        val popupBinding = PopupBinding.inflate(layoutInflater)
        val popupDialog = AlertDialog.Builder(requireContext())
            .setView(popupBinding.root)
            .create()

        val englishWordEditText = popupBinding.englishWord
        val frenchWordEditText = popupBinding.frenchWord
        val addWordButton = popupBinding.addWordButton
        val closeButton = popupBinding.closeButton
        val elephantImage = popupBinding.elephantImageView

        elephantImage.setImageResource(R.drawable.happy_elephant)
        frenchWordEditText.text = word.fr
        englishWordEditText.text = word.en

        closeButton.setOnClickListener {
            popupDialog.dismiss()
        }

        addWordButton.setOnClickListener {
            clickWord(word)
            popupDialog.dismiss()
        }
        popupDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}