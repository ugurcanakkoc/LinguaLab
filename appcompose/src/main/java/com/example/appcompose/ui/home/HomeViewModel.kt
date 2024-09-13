package com.example.appcompose.ui.home

import androidx.lifecycle.ViewModel
import com.example.appcompose.data.model.Word
import com.example.appcompose.data.model.getBaseAllWords
import com.example.appcompose.data.sharedpreferences.SharedPreferencesRepository
import com.example.appcompose.service.TtsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val spRepository: SharedPreferencesRepository,
    private val ttsService: TtsService,
) : ViewModel() {

    private val allWord = getBaseAllWords()
    private val checkedList = mutableListOf<Word>()
    private val _wordList = MutableStateFlow<List<Word>>(emptyList())
    val wordList: StateFlow<List<Word>> = _wordList
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    var isShuffled = false

    init {
        checkedWordList()
    }


    fun swipeRefresh() {
        _isRefreshing.value = true
        isShuffled = !isShuffled
        checkedWordList()
        _isRefreshing.value = false
    }

    fun checkedWordList() {
        var shuffledWords = allWord
        if (!isShuffled) {
            shuffledWords = allWordShuffled()
        }
        isShuffled = true
        checkedList.clear()
        shuffledWords.forEach {
            if (!spRepository.isWordSaved(it)) {
                checkedList.add(it)
            }
        }
        _wordList.value = checkedList
    }

    private fun allWordShuffled(): List<Word> {
        return allWord.shuffled()
    }

    fun saveAndRemoveWord(word: Word) {
        saveWord(word)
        _wordList.update { currentList ->
            currentList.filter { it != word }
        }
    }

    private fun saveWord(word: Word) {
        spRepository.saveWord(word)
    }

    fun speak(text: String, language: String) {
        if (language == "en")
            ttsService.enSpeak(text)
        else
            ttsService.frSpeak(text)
    }

    fun stop() {
        ttsService.stop()
    }
}
