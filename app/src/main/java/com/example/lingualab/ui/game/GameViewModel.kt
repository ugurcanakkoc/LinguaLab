package com.example.lingualab.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lingualab.data.model.Word
import com.example.lingualab.data.sharedpreferences.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameViewModel @Inject constructor(
    private val spRepository: SharedPreferencesRepository,
) : ViewModel() {

    private val _score = MutableLiveData<Int>().apply { value = 0 }
    val score: LiveData<Int> = _score

    private val _answer = MutableLiveData<String>()
    val answer: LiveData<String> = _answer

    private val _bestScore = MutableLiveData<Int>()
    val bestScore: LiveData<Int> = _bestScore

    private val _questions = MutableLiveData<String>()
    val questions: LiveData<String> = _questions

    private val _learnedWords = MutableLiveData<List<Word>>()
    val learnedWords: LiveData<List<Word>> = _learnedWords

    fun getLearnedWords() {
        _learnedWords.value = spRepository.getLearnedWords() ?: listOf(Word("", "", ""))

        val words = _learnedWords.value
        if (words.isNullOrEmpty()) {
            _questions.value = "No words available"
            _answer.value =  "No words available"
            return
        }

        val word = words.randomOrNull()  // Get a random word safely
        if (word != null) {
            val random = Random.nextBoolean()
            _answer.value = word.tr
            _questions.value = if (random) word.en else word.fr
        } else {
            _questions.value = "No words available"
            _answer.value = "null"
        }
    }

    fun checkAnswer(userAnswer: String): Boolean {
        val correctAnswer = _answer.value ?: return false
        val isCorrect = userAnswer.equals(correctAnswer, ignoreCase = true)
        if (isCorrect) {
            increaseScore()
        } else {
            decreaseScore()
        }
        return isCorrect
    }

    private fun increaseScore() {
        _score.value = (_score.value ?: 0) + 1
        checkBestScore()
    }

    private fun decreaseScore() {
        _score.value = (_score.value ?: 0) - 1
    }

    private fun checkBestScore() {
        val currentScore = _score.value ?: 0
        val currentBestScore = _bestScore.value ?: 0
        if (currentScore > currentBestScore) {
            _bestScore.value = currentScore
        }
    }

    fun reset() {
        _score.value = 0
        _bestScore.value = 0
        getLearnedWords()
    }

    init {
        _score.value = 0
    }
}
