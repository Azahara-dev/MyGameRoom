package com.azaharadev.mygameroom.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import com.azaharadev.mygameroom.data.model.Platform
import com.azaharadev.mygameroom.ui.theme.BadgePC
import com.azaharadev.mygameroom.ui.theme.BadgePS5
import com.azaharadev.mygameroom.ui.theme.BadgeSwitch2
import com.azaharadev.mygameroom.ui.theme.BadgeXbox
import com.azaharadev.mygameroom.ui.theme.MyGameRoomTheme
import com.azaharadev.mygameroom.ui.theme.TextPrimary

@Composable
fun PlatformBadge(platform: Platform) {
    val color = when (platform) {
        Platform.PC -> BadgePC
        Platform.PS5 -> BadgePS5
        Platform.XBOX -> BadgeXbox
        Platform.SWITCH2 -> BadgeSwitch2

    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .border(width = 1.dp, color = Color.White.copy(alpha = 0.15f), shape = RoundedCornerShape(8.dp))
    ) {
        Text(
            text = platform.name,
            color = TextPrimary,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlatformBadgePreview() {
    MyGameRoomTheme {
        PlatformBadge(Platform.PC)
    }
}