package com.example.lingualab.ui.home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.wordList.observe(viewLifecycleOwner, Observer { wordList ->
            adapter = WordListRecyclerViewAdapter(wordList, ::showPopup)
            recyclerView.adapter = adapter
        })
        viewModel.checkedWordList()
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

        val turkishWordEditText = popupBinding.turkishWord
        val englishWordEditText = popupBinding.englishWord
        val addWordButton = popupBinding.addWordButton
        val closeButton = popupBinding.closeButton
        val elephantImage = popupBinding.elephantImageView

        elephantImage.setImageResource(R.drawable.happy_elephant)
        turkishWordEditText.text = word.tr
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