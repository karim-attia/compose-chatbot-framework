package ch.karimattia.composechatbotframework

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.karimattia.compose_chatbot_framework.ChatMessage
import ch.karimattia.compose_chatbot_framework.ChatViewModel
import ch.karimattia.compose_chatbot_framework.MessageProposal
import kotlinx.coroutines.CoroutineScope

class MainActivityViewModelFactory(private val scope: CoroutineScope) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainActivityViewModel(scope = scope) as T
}

class MainActivityViewModel(scope: CoroutineScope) : ChatViewModel() {

    init {
        /**
         * Initialize the ChatViewModel: Process the first message.
         * */
        initialize(
            firstMessage = ::introMessage,
            scope = scope
        )
    }


    /**
     * All message templates.
     * */
    private fun introMessage(): ChatMessage = ChatMessage(
        text = "Hey! Super awesome that you are trying out my Compose Chatbot Framework.",
        autoAdvance = true,
        nextMessage = ::multipleMessages
    )

    private fun multipleMessages(): ChatMessage = ChatMessage(
        text = "You can send multiple messages in a row with a short pause. The message that appears next is specified in the current message.",
        autoAdvance = true,
        nextMessage = ::singleMessageProposal,
    )

    private fun singleMessageProposal(): ChatMessage = ChatMessage(
        text = "You can prompt your user with a single or multiple message proposals.",
        nextMessage = ::messageProposalExplanation,
        messageProposals = proposalsNext
    )

    private val proposalsNext: List<MessageProposal> = listOf(
        messageProposalOf(
            proposalText = "Next",
            insertsMessage = ::proposalNextByUser,
        )
    )

    private fun proposalNextByUser(): ChatMessage = ChatMessage(
        text = "Next",
        isMessageByUser = true,
    )

    private fun messageProposalExplanation(): ChatMessage = ChatMessage(
        text = "You can define per message proposal which message is shown next.",
        autoAdvance = true,
        nextMessage = ::multipleProposals,
    )

    private fun multipleProposals(): ChatMessage = ChatMessage(
        text = "Here's multiple message proposals:",
        messageProposals = multipleMessageProposals()
    )


    private fun multipleMessageProposals(): List<MessageProposal> = listOf(
        messageProposalOf(
            proposalAction = { },
            proposalText = "Return to previous message",
            insertsMessage = ::returnByUser,
        ),
        messageProposalOf(
            proposalAction = { },
            proposalText = "Continue",
            insertsMessage = ::continueByUser,
        )
    )

    private fun returnByUser(): ChatMessage = ChatMessage(
        text = "Return to previous message",
        isMessageByUser = true,
        nextMessage = ::singleMessageProposal,
    )

    private fun continueByUser(): ChatMessage = ChatMessage(
        text = "Continue",
        isMessageByUser = true,
        nextMessage = ::userInput,
    )

    private val userInput: MutableLiveData<String> = MutableLiveData("")

    private fun userInput(): ChatMessage = ChatMessage(
        text = "Get text input from users with a text input field.",
        nextMessage = ::userWrote,
        chatInputField = chatInputFieldOf(
            value = userInput,
            onValueChange = { userInput.value = it },
            insertMessage = ::userInputByUser,
            placeholder = "Try it out.",
        ),
    )

    private fun userInputByUser(): ChatMessage = ChatMessage(
        text = userInput.value!!,
        isMessageByUser = true,
    )

    private fun userWrote(): ChatMessage = ChatMessage(
        text = "You can add any composable below the message. \nIn the input field, you wrote:",
        nextMessage = ::end,
        autoAdvance = true,
        messageExtra = {
            Text(
                text = userInput.value!!,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 0.dp, top = 8.dp, bottom = 0.dp)
                    .width(128.dp)
                    .height(32.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(Color.LightGray)
                    .wrapContentSize(Alignment.Center)
            )
        }
    )

    private fun end(): ChatMessage = ChatMessage(
        text = "This is the end of the demo. Let me know what you think.",
        messageProposals = startOver
    )

    // Message proposal to start over.
    private val startOver: List<MessageProposal> = listOf(
        messageProposalOf(
            proposalAction = { },
            proposalText = "Start over",
            insertsMessage = ::introMessage,
        )
    )
}