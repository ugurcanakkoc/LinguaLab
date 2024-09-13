package com.example.lingualab.data.sharedpreferences

import android.content.SharedPreferences
import com.example.lingualab.data.model.Word
import com.example.lingualab.data.model.getBaseAllWords
import javax.inject.Inject

class SharedPreferencesRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun isWordSaved(word: Word): Boolean {
        val savedWord = sharedPreferences.getString(word.en, null)
        return savedWord != null
    }

    fun getLearnedWords(): List<Word> {
        val learnedWords = mutableListOf<Word>()

        val allEntries = sharedPreferences.all
        for ((key, value) in allEntries) {
            getBaseAllWords().forEach {
                if (it.en == key) {
                    val word = Word(en = key, tr = value as String, fr = it.fr)
                    learnedWords.add(word)

                }
            }
        }
        return learnedWords
    }


    fun saveWord(word: Word) {
        sharedPreferences.edit()?.putString(word.en, word.tr)?.apply()
    }

    fun getWord(word: Word): String? {
        return sharedPreferences.getString(word.en, null)
    }

    fun deleteWord(word: Word) {
        sharedPreferences.edit()?.remove(word.en)?.apply()
    }
}
