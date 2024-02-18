package com.example.planning.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planning.di.myModules
import com.example.planning.ui.feature.Planning.PlanningScreen
import com.example.planning.ui.feature.detailNotes.DetailNoteScreen
import com.example.planning.ui.feature.detailProject.DetailsProjectScreen
import com.example.planning.ui.feature.main.MainScreen
import com.example.planning.ui.feature.notes.NoteScreen
import com.example.planning.ui.feature.project.ProjectScreen
import com.example.planning.ui.feature.search.SearchScreen
import com.example.planning.ui.feature.setting.SettingScreen
import com.example.planning.util.Mysreens
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myModules)
            }){
                PlanningUi()

            }
        }
    }
}
@Composable
fun PlanningUi() {
    val navController = rememberNavController()
    KoinNavHost(navController = navController, startDestination = Mysreens.MainScreen.route){
        composable(Mysreens.MainScreen.route){
            MainScreen()
        }
        composable(Mysreens.ProjectScreen.route){
            ProjectScreen()
        }
        composable(Mysreens.DetailsProjectScreen.route){
            DetailsProjectScreen()
        }
        composable(Mysreens.NotesScreen.route){
            NoteScreen()
        }
        composable(Mysreens.DetailNoteScreen.route){
            DetailNoteScreen()
        }
        composable(Mysreens.Planning.route){
            PlanningScreen()
        }
        composable(Mysreens.Setting.route){
            SettingScreen()
        }
        composable(Mysreens.Search.route){
            SearchScreen()
        }
    }

}