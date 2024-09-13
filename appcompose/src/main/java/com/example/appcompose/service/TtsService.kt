package com.example.appcompose.service

import android.speech.tts.TextToSpeech
import androidx.compose.ui.text.intl.Locale
import javax.inject.Inject

class TtsService@Inject constructor(
    private val textToSpeech: TextToSpeech,
) {


    fun enSpeak(text: String) {
        textToSpeech.setLanguage(java.util.Locale.US)
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun frSpeak(text: String) {
        textToSpeech.setLanguage(java.util.Locale.FRANCE)
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }



    fun stop() {
        textToSpeech.stop()
    }


}