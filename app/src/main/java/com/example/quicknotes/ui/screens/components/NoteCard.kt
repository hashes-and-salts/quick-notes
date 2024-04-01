package com.example.quicknotes.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quicknotes.R
import com.example.quicknotes.data.note.NoteEntity
import java.text.DateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NoteCard(noteEntity: NoteEntity, onClick: () -> Unit) {

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth(1f),
        colors = CardDefaults.cardColors(
            containerColor = Color(noteEntity.color.toInt()),
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // Note Content
            Text(
                text = noteEntity.content,
                style = MaterialTheme.typography.bodyMedium,
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

@Preview
@Composable
private fun NoteCardPreview() {

    val noteEntity = NoteEntity(
        "This is a title",
        "This is the content of the note",
        System.currentTimeMillis(),
    )

    NoteCard(noteEntity = noteEntity, onClick = {})
    
}

fun formatTime(timeStamp: Long): String {
    val dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault())
    return dateFormatter.format(Date(timeStamp))
}
