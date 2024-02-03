package ch.karimattia.compose_chatbot_framework

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ChatMainScreen(
	chatViewModel: ChatViewModel,
	spacerTop: Dp = 8.dp,
) {
	ChatMainScreen(
		shownMessages = chatViewModel.shownMessages,
		scrollState = chatViewModel.scrollState,
		spacerTop = spacerTop,
	)
}

@Composable
fun ChatMainScreen(
	shownMessages: List<ChatMessage>,
	scrollState: ScrollState,
	spacerTop: Dp,
) {
	val lastMessage = shownMessages.lastOrNull()
	Column {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.verticalScroll(scrollState)
				.weight(1f)
		) {
			Spacer(Modifier.height(spacerTop))
			for (message in shownMessages) {
				MessageCard(message = message)
			}
		}

		lastMessage?.bottomArea?.invoke()
	}
}