package com.example.appcompose.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.speech.tts.TextToSpeech
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("LinguaLabPrefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideTextToSpeech(application: Application): TextToSpeech {
        return TextToSpeech(application, null)
    }
}
