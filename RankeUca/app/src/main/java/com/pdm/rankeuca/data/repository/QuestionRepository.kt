package com.pdm.rankeuca.data.repository


import com.pdm.rankeuca.data.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    fun getQuestions(): Flow<List<Question>>
    suspend fun addQuestion(title: String)
    suspend fun deleteQuestion(question: Question)
}

