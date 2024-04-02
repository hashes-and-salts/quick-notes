package com.example.quicknotes.ui.screens.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quicknotes.R
import com.example.quicknotes.data.note.NoteEntity
import com.example.quicknotes.utils.formatTime
import com.example.quicknotes.utils.getNoteCardColor
@Composable
fun NoteCard(noteEntity: NoteEntity, onCardClick: () -> Unit, onCardLongPress:() -> Unit) {

    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 16.dp
        ),

    ) {

        OutlinedCard (
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 300.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            onCardLongPress()
                        },
                        onTap = {
                            onCardClick()
                        },
                    )
                },
            colors = CardDefaults.outlinedCardColors(
                containerColor = getNoteCardColor(noteEntity.color, isSystemInDarkTheme()) ?: Color.Transparent
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {


                // Note Content
                Text(
                    text = noteEntity.content,
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily(Font(R.font.playfairdisplay_regular)),
                )

                // Note Title
                Text(
                    text = noteEntity.title,
                    style = MaterialTheme.typography.labelLarge,
                    fontFamily = FontFamily(Font(R.font.playfairdisplay_extrabold)),
                    modifier = Modifier.padding(top = 8.dp)
                )

                // Date
                Text(
                    text = formatTime(noteEntity.dateEdited),
                    style = MaterialTheme.typography.labelMedium,
                    fontFamily = FontFamily(Font(R.font.playfairdisplay_extrabold)),
                )

            }

        }

    }

}

@Preview
@Composable
private fun NoteCardPreview() {
    val noteEntity = NoteEntity(
        "This is a title",
        "This is the content of the note",
        System.currentTimeMillis(),
    )
    NoteCard(noteEntity = noteEntity, onCardClick = {}, onCardLongPress = {})
}
