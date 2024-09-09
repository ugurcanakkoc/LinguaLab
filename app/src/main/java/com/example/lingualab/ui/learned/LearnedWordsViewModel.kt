package com.example.lingualab.ui.learned

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lingualab.data.model.Word
import com.example.lingualab.data.sharedpreferences.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LearnedWordsViewModel @Inject constructor(private val spRepository: SharedPreferencesRepository)  : ViewModel() {

    private val _learnedWords = MutableLiveData<List<Word>>()
    val learnedWords : LiveData<List<Word>> get() = _learnedWords

    init {
        checkWordList()
    }

    fun checkWordList(){
        _learnedWords.value = spRepository.getLearnedWords()

    }

    fun deleteWord(word: Word) {
        spRepository.deleteWord(word)
        _learnedWords.value = spRepository.getLearnedWords()


    }




}