package com.example.appcompose.data.model

import com.example.appcompose.R

sealed class Screen(val route: String, val title: String, val icon: Int) {
    data object WordList : Screen("wordlist", "Word List", R.drawable.ic_wordlist)
    data object LearnedWords : Screen("learnedwords", "Learned Words", R.drawable.ic_learned_words)
}
