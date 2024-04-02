package com.example.quicknotes.ui.screens.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {

    val homeScreenState by remember {
        homeViewModel.state
    }.collectAsState()

    val ctx = LocalContext.current

    val bottomSheetState = rememberModalBottomSheetState()
    var bottomSheetVisible by remember {
        mutableStateOf(false)
    }

//    val selectedNote by remember {
//        homeViewModel.selectedNoteEntity
//    }.collectAsState()

    val coroutineScope = rememberCoroutineScope()

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
                    itemsIndexed(homeScreenState) {i, note ->
                        NoteCard(
                            noteEntity = note,
                            onCardClick = {
                                Toast.makeText(ctx, "TODO: Implement Click Action", Toast.LENGTH_SHORT).show()
                            },
                            onCardLongPress = {
                                Log.d("CARD", "HomeScreen: long pressed")
                                bottomSheetVisible = true
                                homeViewModel.updateSelectedNote(note)
//                                Toast.makeText(ctx, "TODO: Implement Long Press Action", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }



                }



            }

        }

        if (bottomSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = { bottomSheetVisible = false },
                sheetState = bottomSheetState
            ) {

                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxHeight(0.8f)
                        .fillMaxWidth()
                ) {

                    TextButton(onClick = {
                        homeViewModel.deleteNote()

                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }.invokeOnCompletion {
                            bottomSheetVisible = false
                        }
                    }) {

                        Text(text = "Delete Note")

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