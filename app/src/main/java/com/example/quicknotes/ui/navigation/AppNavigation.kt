package com.example.quicknotes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quicknotes.ui.screens.add_note.AddNoteScreen
import com.example.quicknotes.ui.screens.add_note.AddNoteViewModel
import com.example.quicknotes.ui.screens.home.HomeScreen
import com.example.quicknotes.ui.screens.home.HomeViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.HomeScreen.name ) {

        composable(route = Screens.HomeScreen.name) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(navController = navController, homeViewModel = homeViewModel)
        }
        composable(route = Screens.AddNoteScreen.name) {
            val addNoteViewModel = hiltViewModel<AddNoteViewModel>()
            AddNoteScreen(navController = navController, addNoteViewModel = addNoteViewModel)
        }
        composable(route = Screens.EditNoteScreen.name) {
            // Edit Note Screen
        }
    }

}