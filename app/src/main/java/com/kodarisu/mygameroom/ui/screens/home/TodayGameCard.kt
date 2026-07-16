package com.kodarisu.mygameroom.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kodarisu.mygameroom.data.model.Game
import com.kodarisu.mygameroom.data.model.MockData
import com.kodarisu.mygameroom.ui.theme.MyGameRoomTheme
import com.kodarisu.mygameroom.ui.theme.TextPrimary

@Composable
fun TodayGameCard(game: Game, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(140.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable{ onClick() },
        contentAlignment = Alignment.BottomStart
    ) {

        AsyncImage(
            model = game.image,
            contentDescription = game.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.85f))
                    )
                )
        )

        Text(
            text = game.title,
            color = TextPrimary,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodayGameCardPreview() {
    MyGameRoomTheme {
        TodayGameCard(MockData.games.get(0), {})
    }
}