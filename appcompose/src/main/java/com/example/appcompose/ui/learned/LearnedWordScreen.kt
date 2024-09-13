package com.example.appcompose.ui.learned

import WordPopup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appcompose.R
import com.example.appcompose.data.model.Word
import com.example.appcompose.ui.home.WordCard

@Composable
fun LearnedWordScreen(viewModel: LearnedWordsViewModel) {
    var showPopup by remember { mutableStateOf(false) }
    var selectedWord by remember { mutableStateOf<Word?>(null) }
    val words by viewModel.learnedWords.observeAsState(emptyList())

    Box {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(words) { word ->
                WordCard(
                    word = word,
                    onClick = {
                        selectedWord = word
                        showPopup = true
                    }
                )
            }
            if (words.isEmpty()) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = "Elephant",
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.3f)
                            .align(Alignment.Center),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }

    selectedWord?.let { word ->
        if (showPopup) {
            WordPopup(
                onDismiss = { showPopup = false },
                englishWord = word.en,
                frenchWord = word.fr!!,
                englishSoundIcon = painterResource(id = R.drawable.ic_sound),
                frenchSoundIcon = painterResource(id = R.drawable.ic_sound),
                elephantIcon = painterResource(id = R.drawable.sad_elephant),
                onAddWordClick = {
                    viewModel.deleteWord(word)
                    showPopup = false
                },
                enClickSound = {
                    viewModel.speak(word.en, "en")
                },
                frClickSound = {
                    viewModel.speak(word.fr, "fr")
                },
                message = "Unuttum..."
            )
        }
    }
}

