package com.pdm.rankeuca.data.repository

import com.pdm.rankeuca.data.database.dao.QuestionDao
import com.pdm.rankeuca.data.database.entities.QuestionEntity
import com.pdm.rankeuca.data.database.entities.toModel
import com.pdm.rankeuca.data.model.Question
import com.pdm.rankeuca.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class QuestionRepositoryImpl(
    private val questionDao: QuestionDao
) : QuestionRepository {

    override fun getQuestions(): Flow<List<Question>> {
        return questionDao.getQuestionsWithOptions().map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun addQuestion(title: String) {
        questionDao.insertQuestion(QuestionEntity(title = title))
    }

    override suspend fun deleteQuestion(question: Question) {
        questionDao.deleteQuestion(question.toEntity())
    }
}