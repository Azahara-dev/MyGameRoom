package com.kodarisu.mygameroom.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodarisu.mygameroom.ui.theme.AccentPrimary
import com.kodarisu.mygameroom.ui.theme.MyGameRoomTheme
import com.kodarisu.mygameroom.ui.theme.SurfaceDark
import com.kodarisu.mygameroom.ui.theme.TextPrimary
import com.kodarisu.mygameroom.ui.theme.TextSecondary

@Composable
fun GenreChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor: Color = if (isSelected) AccentPrimary else SurfaceDark
    val textColor: Color = if (isSelected) TextPrimary else TextSecondary

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .clickable() { onClick() }
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GenreChipPreview() {
    MyGameRoomTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            GenreChip("Todos", true, {})
            GenreChip("Acción", false, {})
            GenreChip("RPG", false, {})
            GenreChip("Deportes", false, {})
        }
    }
}