package com.pdm.rankeuca.data

import com.pdm.rankeuca.data.repository.OptionRepository
import android.content.Context
import com.pdm.rankeuca.data.database.AppDatabase
import com.pdm.rankeuca.data.repository.OptionRepositoryImpl
import com.pdm.rankeuca.data.repository.QuestionRepository
import com.pdm.rankeuca.data.repository.QuestionRepositoryImpl

class AppProvider(context: Context) {

    private val appDatabase = AppDatabase.getDatabase(context)
    private val questionDao = appDatabase.questionDao()
    private val optionDao = appDatabase.optionDao()


    private val questionRepository: QuestionRepository =
        QuestionRepositoryImpl(questionDao)
    private val optionRepository: OptionRepository =
        OptionRepositoryImpl(optionDao)

    fun provideQuestionRepository(): QuestionRepository {
        return questionRepository
    }

    fun provideOptionRepository(): OptionRepository {
        return optionRepository
    }
}