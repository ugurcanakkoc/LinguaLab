package com.example.lingualab.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lingualab.data.model.Word
import com.example.lingualab.data.sharedpreferences.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val spRepository: SharedPreferencesRepository) :
    ViewModel() {

    private val allWord = listOf(
        Word(en = "Apple", tr = "Elma"),
        Word(en = "Ball", tr = "Top"),
        Word(en = "Cat", tr = "Kedi"),
        Word(en = "Dog", tr = "Köpek"),
        Word(en = "Elephant", tr = "Fil"),
        Word(en = "Fish", tr = "Balık"),
        Word(en = "Giraffe", tr = "Zürafa"),
        Word(en = "Hat", tr = "Şapka"),
        Word(en = "Ice cream", tr = "Dondurma"),
        Word(en = "Jump", tr = "Zıplamak"),
        Word(en = "Kite", tr = "Uçurtma"),
        Word(en = "Lion", tr = "Aslan"),
        Word(en = "Monkey", tr = "Maymun"),
        Word(en = "Nest", tr = "Yuva"),
        Word(en = "Orange", tr = "Portakal"),
        Word(en = "Penguin", tr = "Penguen"),
        Word(en = "Queen", tr = "Kraliçe"),
        Word(en = "Rainbow", tr = "Gökkuşağı"),
        Word(en = "Sun", tr = "Güneş"),
        Word(en = "Tree", tr = "Ağaç"),
        Word(en = "Umbrella", tr = "Şemsiye"),
        Word(en = "Van", tr = "Van"),
        Word(en = "Water", tr = "Su"),
        Word(en = "X-ray", tr = "Röntgen"),
        Word(en = "Yo-yo", tr = "Yo-yo"),
        Word(en = "Zebra", tr = "Zebra"),
        Word(en = "Chair", tr = "Sandalye"),
        Word(en = "Table", tr = "Masa"),
        Word(en = "Bed", tr = "Yatak"),
        Word(en = "Car", tr = "Araba"),
        Word(en = "Train", tr = "Tren"),
        Word(en = "Book", tr = "Kitap"),
        Word(en = "School", tr = "Okul"),
        Word(en = "Shirt", tr = "Gömlek"),
        Word(en = "Pants", tr = "Pantolon"),
        Word(en = "Shoes", tr = "Ayakkabı"),
        Word(en = "Socks", tr = "Çorap"),
        Word(en = "Hand", tr = "El"),
        Word(en = "Foot", tr = "Ayak"),
        Word(en = "Nose", tr = "Burun"),
        Word(en = "Ear", tr = "Kulak"),
        Word(en = "Eye", tr = "Göz"),
        Word(en = "Mouth", tr = "Ağız"),
        Word(en = "Face", tr = "Yüz"),
        Word(en = "House", tr = "Ev"),
        Word(en = "Door", tr = "Kapı"),
        Word(en = "Window", tr = "Pencere"),
        Word(en = "Farm", tr = "Çiftlik"),
        Word(en = "Cow", tr = "İnek"),
        Word(en = "Chicken", tr = "Tavuk")
    ).shuffled()
    private val checkedList = mutableListOf<Word>()
    private val _wordList = MutableLiveData<List<Word>>()
    val wordList: LiveData<List<Word>> = _wordList

    init {
        checkedWordList()
    }

    fun checkedWordList() {
        checkedList.clear()
        allWord.forEach {
            if (!spRepository.isWordSaved(it)) {
                checkedList.add(it)
            }
        }
        _wordList.value = checkedList
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