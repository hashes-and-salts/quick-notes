package com.example.quicknotes.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quicknotes.R
import com.example.quicknotes.ui.navigation.Screens
import com.example.quicknotes.ui.screens.components.NoteCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {

    val homeScreenState by remember {
        homeViewModel.state
    }.collectAsState()

    Scaffold(
//        topBar = { HomeTopBar() },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(route = Screens.AddNoteScreen.name) }, shape = CircleShape) {

                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Create Note")

            }
        }
    ) { paddingValues ->

        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {

            Column {

                Text(
                    text = "My Notes",
                    modifier = Modifier
                        .padding(
                            top = 32.dp,
                            start = 24.dp,
                            end = 24.dp
                    ),
                    fontFamily = FontFamily(Font(R.font.playfairdisplay_regular)),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalItemSpacing = 16.dp
                ) {
                    items(homeScreenState) { note ->
                        NoteCard(noteEntity = note) {
                            // onClick
                        }
                    }



                }



            }

        }

    }
}

@Preview
@Composable
fun HomePreview() {

    HomeScreen(navController = rememberNavController(), viewModel())

}