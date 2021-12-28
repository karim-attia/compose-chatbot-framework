package ch.karimattia.composechatbotframework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ch.karimattia.composechatbotframework.ui.theme.ComposeChatbotFrameworkTheme
import ch.karimattia.compose_chatbot_framework.ChatMainScreen
import ch.karimattia.compose_chatbot_framework.ChatViewModel
import ch.karimattia.composechatbotframework.ui.theme.GrayBackgroundColor

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChatbotFrameworkTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = GrayBackgroundColor) {
                    Content()
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun Content(mainActivityViewModel: ChatViewModel = viewModel(factory = MainActivityViewModelFactory(scope = rememberCoroutineScope()))) {
    ChatMainScreen(
        chatViewModel = mainActivityViewModel
    )
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeChatbotFrameworkTheme {
        Content()
    }
}