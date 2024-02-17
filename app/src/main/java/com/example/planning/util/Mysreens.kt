package com.example.planning.util

sealed class Mysreens(val route: String){
     object MainScreen : Mysreens("mainScreen")
     object ProjectScreen : Mysreens("projectScreen")
     object DetailsProjectScreen : Mysreens("detailsProjectScreen")
     object NotesScreen : Mysreens("notesScreen")
     object DetailNoteScreen : Mysreens("detailNoteScreen")
     object Planning : Mysreens("planning")
     object Setting : Mysreens("setting")
     object Search : Mysreens("search")


}