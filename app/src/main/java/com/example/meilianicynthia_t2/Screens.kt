package com.example.meilianicynthia_t2

sealed class Screens (val screen: String){
    data object Home: Screens("home")
    data object Favorite: Screens("favorite_1")
    data object Person: Screens("Person_1")
    data object DetailView: Screens("Detailview")
}