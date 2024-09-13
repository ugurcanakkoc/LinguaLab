package com.example.lingualab.ui.game

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.lingualab.R
import com.example.lingualab.databinding.FragmentGameBinding
import com.example.lingualab.databinding.GamePopupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {


    private lateinit var binding: FragmentGameBinding
    private val viewModel: GameViewModel by viewModels()
    private lateinit var wordEditText: EditText
    private lateinit var checkButton: Button
    private lateinit var wordTextView: TextView
    private lateinit var elephantImageView: ImageView
    private lateinit var refreshWord: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        wordEditText = binding.wordEditText
        checkButton = binding.checkButton
        wordTextView = binding.wordTextView
        refreshWord = binding.refreshWord

        refreshWord()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.questions.observe(viewLifecycleOwner) {
            wordTextView.text = it
        }
        viewModel.learnedWords.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.emptyPage.visibility = View.VISIBLE
                binding.cardView.visibility = View.GONE
                binding.checkButton.visibility = View.GONE
            } else {
                binding.emptyPage.visibility = View.GONE
                binding.cardView.visibility = View.VISIBLE
                binding.checkButton.visibility = View.VISIBLE
            }
        }

        viewModel.score.observe(viewLifecycleOwner) {
            binding.score.text = it.toString()
        }
        checkButton.setOnClickListener {
            val enteredWord = wordEditText.text.toString()
            messagePopup(viewModel.checkAnswer(enteredWord))

        }
        refreshWord.setOnClickListener {
            refreshWord()
        }
    }

    private fun messagePopup(isTrue: Boolean) {
        val popupBinding = GamePopupBinding.inflate(layoutInflater)
        val popupDialog = AlertDialog.Builder(requireContext())
            .setView(popupBinding.root)
            .create()

        val messageTextView = popupBinding.messageTextView
        val nextButton = popupBinding.nextButton
        val elephantImageView = popupBinding.elephantImageView

        if (isTrue) {
            messageTextView.text = "DOĞRU CEVAP!!"
            elephantImageView.setImageResource(R.drawable.happy_elephant_2)
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.move)
            elephantImageView.startAnimation(animation)
        } else if (viewModel.score.value!! <= 0) {

            messageTextView.text =
                "Oyun Bitti. \n En yüksek puanın: ${viewModel.bestScore.value}\n Tekrar Başlayalım!"
            elephantImageView.setImageResource(R.drawable.happy_elephant_3)
            nextButton.setOnClickListener {
                viewModel.reset()
                popupDialog.dismiss()
            }


        } else {
            messageTextView.text = "YANLIŞ CEVAP!!"
            elephantImageView.setImageResource(R.drawable.sad_elephant)
        }


        nextButton.setOnClickListener {
            refreshWord()
            popupDialog.dismiss()
        }
        popupDialog.show()
    }

    private fun refreshWord() {
        viewModel.getLearnedWords()
        wordEditText.text.clear()


    }
}