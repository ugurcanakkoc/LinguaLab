package com.example.lingualab.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lingualab.data.model.Word
import com.example.lingualab.data.model.getBaseAllWords
import com.example.lingualab.data.sharedpreferences.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val spRepository: SharedPreferencesRepository) :
    ViewModel() {


    private val allWord =  getBaseAllWords()
    private val checkedList = mutableListOf<Word>()
    private val _wordList = MutableLiveData<List<Word>>()
    val wordList: LiveData<List<Word>> = _wordList
    var isShuffled = false

    init {
        checkedWordList()
    }

    fun swipeRefresh(){

        isShuffled = !isShuffled
        checkedWordList()

    }

    fun checkedWordList() {
        var shuffledWords = allWord
        if (!isShuffled)
            shuffledWords = allWordShuffled()
            isShuffled=true
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
        val updatedList = _wordList.value?.toMutableList()
        updatedList?.remove(word)
        _wordList.value = updatedList!!
    }

    private fun saveWord(word: Word) {
        spRepository.saveWord(word)
    }
}