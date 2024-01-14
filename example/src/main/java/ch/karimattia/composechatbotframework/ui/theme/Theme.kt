package ch.karimattia.composechatbotframework.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun ComposeChatbotFrameworkTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Primary,
            secondary = Secondary,
            onPrimary = Color.White,
            onSecondary = Color.White,
            onBackground = TextBlackColor,
            surface = Color.White,
            onSurface = TextBlackColor,
            background = GrayBackgroundColor,
        ),
        // typography = Typography,
        // shapes = Shapes,
        content = content
    )
}