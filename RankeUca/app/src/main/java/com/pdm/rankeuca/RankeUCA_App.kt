package com.pdm.rankeuca

import androidx.compose.runtime.Composable
import com.pdm.rankeuca.screen.Option.OptionsScreen
import com.pdm.rankeuca.screen.Question.QuestionsScreen
import com.pdm.rankeuca.router.Routes
import com.pdm.rankeuca.screen.Home.HomeScreen
import com.pdm.rankeuca.screen.Resultados.ResultadosScreen
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

import androidx.navigation3.runtime.entryProvider
@Composable
fun RankeUCA_App() {
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Home> {
                HomeScreen(
                    onIrResultados = { backStack.add(Routes.Resultados) },
                    onIrOptions = { backStack.add(Routes.Options) },
                    onIrQuestions = { backStack.add(Routes.Questions) }
                )
            }
            entry<Routes.Resultados> {
                ResultadosScreen(
                    onVolver = { backStack.removeLastOrNull() }
                )
            }
            entry<Routes.Options> {
                OptionsScreen(
                    questionId = 1,
                    onVolver = { backStack.removeLastOrNull() }
                )
            }

            entry<Routes.Questions> {
                QuestionsScreen(
                    onQuestionClick = { id -> backStack.add(Routes.Options) },
                    onVolver = { backStack.removeLastOrNull() }
                )
            }

        },
    )
}
