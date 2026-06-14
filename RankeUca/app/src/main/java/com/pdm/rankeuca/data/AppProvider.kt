package com.pdm.rankeuca.data

import com.pdm.rankeuca.data.repository.OptionRepository
import android.content.Context
import com.pdm.rankeuca.data.database.AppDatabase
import com.pdm.rankeuca.data.repository.OptionRepositoryImpl


class AppProvider(context: Context) {

    private val appDatabase = AppDatabase.getDatabase(context)
    private val optionDao = appDatabase.optionDao()

    private val optionRepository: OptionRepository =
        OptionRepositoryImpl(optionDao)

    fun provideOptionRepository(): OptionRepository {
        return optionRepository
    }
}