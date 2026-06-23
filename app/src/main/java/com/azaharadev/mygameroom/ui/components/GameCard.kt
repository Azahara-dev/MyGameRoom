package com.azaharadev.mygameroom.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.azaharadev.mygameroom.data.model.Game
import com.azaharadev.mygameroom.data.model.MockData
import com.azaharadev.mygameroom.ui.theme.FavoritePink
import com.azaharadev.mygameroom.ui.theme.MyGameRoomTheme
import com.azaharadev.mygameroom.ui.theme.StarGold
import com.azaharadev.mygameroom.ui.theme.SurfaceDark
import com.azaharadev.mygameroom.ui.theme.TextPrimary
import com.azaharadev.mygameroom.ui.theme.TextSecondary
import kotlin.math.roundToInt

@Composable
fun GameCard(
    game: Game,
    onFavoriteClick: () -> Unit,
    onCardClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(SurfaceDark)
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onCardClick() }
            .padding(all = 10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(IntrinsicSize.Min)) {
            AsyncImage(
                model = game.image,
                contentDescription = game.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(80.dp)
                    .height(110.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight()) {

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(3.dp),) {
                        game.platforms.forEach { platform ->
                            PlatformBadge(platform = platform)
                        }
                    }

                    Text(
                        text = game.title,
                        color = TextPrimary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = game.developer,
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row() {
                            val fullStars = game.mark.roundToInt()

                            for (i in 0 until 5) {
                                if (i < fullStars) {
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = null,
                                        tint = StarGold,
                                        modifier = Modifier.size(14.dp)
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Filled.StarBorder,
                                        contentDescription = null,
                                        tint = Color.White.copy(alpha = 0.2f),
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }

                        Text(
                            text = "%.1f".format(game.mark),
                            color = StarGold,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    Icon(
                        imageVector = if (game.isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = null,
                        tint = if (game.isFavourite) FavoritePink else Color.White.copy(alpha = 0.2f),
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onFavoriteClick() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameCardPreview() {
    MyGameRoomTheme {
        GameCard(MockData.games.get(0), {}, {})
    }
}
