package com.example.lingualab.ui.learned

import android.app.AlertDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lingualab.R
import com.example.lingualab.data.model.Word
import com.example.lingualab.databinding.FragmentLearnedWordsBinding
import com.example.lingualab.databinding.PopupBinding
import com.example.lingualab.ui.learned.adapter.LearnedWordListRecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnedWordsFragment : Fragment() {
    private var _binding: FragmentLearnedWordsBinding? = null
    private val binding get() = _binding!!

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
                showPopup(word)
            }
            binding.rvLearnedWordList.adapter = viewAdapter
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

        elephantImage.setImageResource(R.drawable.sad_elephant)
        turkishWordEditText.text = word.tr
        englishWordEditText.text = word.en
        addWordButton.text = "Unuttum..."

        closeButton.setOnClickListener {
            popupDialog.dismiss()
        }

        addWordButton.setOnClickListener {
            viewModel.deleteWord(word)
            popupDialog.dismiss()
        }

        popupDialog.show()
    }
}