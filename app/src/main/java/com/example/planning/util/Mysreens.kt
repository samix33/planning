package com.example.planning.util

sealed class Mysreens(val route: String){
     object MainScreen : Mysreens("mainScreen")
     object ProjectScreen : Mysreens("ProjectScreen")
     object DetailsProjectScreen : Mysreens("DetailsProjectScreen")
     object NotesScreen : Mysreens("NotesScreen")

}