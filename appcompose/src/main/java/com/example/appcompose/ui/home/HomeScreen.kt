package com.example.appcompose.ui.home

import WordPopup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appcompose.R
import com.example.appcompose.data.model.Word
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    var showPopup by remember { mutableStateOf(false) }
    var selectedWord by remember { mutableStateOf<Word?>(null) }
    val words by viewModel.wordList.collectAsState(emptyList())
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.checkedWordList()
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            viewModel.swipeRefresh()
            words.forEach {
                println(it.tr)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (words.isNotEmpty())
                items(words) { word ->
                    WordCard(
                        word = word,
                        onClick = {
                            selectedWord = word
                            showPopup = true
                        }
                    )
                }
        }
    }

    selectedWord?.let {
        if (showPopup)
            WordPopup(
                onDismiss = { showPopup = false },
                englishWord = it.en,
                frenchWord = it.fr!!,
                englishSoundIcon = painterResource(id = R.drawable.ic_sound),
                frenchSoundIcon = painterResource(id = R.drawable.ic_sound),
                popupIcon = painterResource(id = R.drawable.ic_popup),
                elephantIcon = painterResource(id = R.drawable.happy_elephant),
                onAddWordClick = {
                    viewModel.saveAndRemoveWord(it)
                    showPopup = false
                },
                enClickSound = {
                    viewModel.speak(it.en, "en")
                },
                frClickSound = {
                    viewModel.speak(it.fr, "fr")
                },
                message = "Kelimeyi Ekle"
            )
    }
}

@Composable
fun WordCard(
    word: Word,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = word.tr,
            modifier = Modifier.padding(16.dp)
        )
    }
}
