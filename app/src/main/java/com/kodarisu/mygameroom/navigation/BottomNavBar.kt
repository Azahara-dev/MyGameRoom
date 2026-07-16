package com.kodarisu.mygameroom.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodarisu.mygameroom.ui.theme.AccentPrimary
import com.kodarisu.mygameroom.ui.theme.MyGameRoomTheme
import com.kodarisu.mygameroom.ui.theme.SurfaceDark
import com.kodarisu.mygameroom.ui.theme.TextSecondary

@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceDark)
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BottomNavItem(
            icon = Icons.Filled.Home,
            isSelected = currentRoute == Routes.HOME,
            title = "Inicio",
            onClick = { onNavigate(Routes.HOME) })

        BottomNavItem(
            icon = Icons.Filled.Explore,
            isSelected = currentRoute == Routes.EXPLORE,
            title = "Explorar",
            onClick = { onNavigate(Routes.EXPLORE) })

        BottomNavItem(
            icon = Icons.Filled.Favorite,
            isSelected = currentRoute == Routes.FAVORITES,
            title = "Favoritos",
            onClick = { onNavigate(Routes.FAVORITES) })
    }

}

@Composable
fun BottomNavItem(
    icon: ImageVector,
    isSelected: Boolean,
    title: String,
    onClick: () -> Unit
) {

    val tintColor by animateColorAsState(
        targetValue = if (isSelected) AccentPrimary else TextSecondary,
        label = "navItemTint"
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) AccentPrimary.copy(alpha = 0.15f) else Color.Transparent,
        label = "navItemBackground"
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(backgroundColor)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tintColor
            )
        }

        Text(
            text = title,
            color = tintColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavItemPreview() {
    MyGameRoomTheme {
        BottomNavItem(Icons.Filled.Home, true, "Inicio", {})
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    MyGameRoomTheme {
        BottomNavBar("home", {})
    }
}