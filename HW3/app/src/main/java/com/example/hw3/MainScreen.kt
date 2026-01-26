package com.example.hw3

import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter


data class Message (val author: UserViewModel, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all=8.dp)) {

        Image (
            painter = rememberAsyncImagePainter(Uri.parse(msg.author.avatarUri)),
            contentDescription = "Profile image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, color = MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember {mutableStateOf(false)}

        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )
        Column(modifier = Modifier.clickable {isExpanded = !isExpanded}) {
            Text(
                text = msg.author.name,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

/**
 * SampleData for Jetpack Compose Tutorial, modified
 */
val messageBodies = listOf(
    "Test...Test...Test...",

    """List of Android versions:
    |Android KitKat (API 19)
    |Android Lollipop (API 21)
    |Android Marshmallow (API 23)
    |Android Nougat (API 24)
    |Android Oreo (API 26)
    |Android Pie (API 28)
    |Android 10 (API 29)
    |Android 11 (API 30)
    |Android 12 (API 31)""".trim(),

    """I think Kotlin is my favorite programming language.
    |It's so much fun!""".trim(),

    "Searching for alternatives to XML layouts...",


    """Hey, take a look at Jetpack Compose, it's great!
    |It's the Android's modern toolkit for building native UI.
    |It simplifies and accelerates UI development on Android.
    |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim(),

    "It's available from API 21+ :)",

    "Writing Kotlin for UI seems so natural, Compose where have you been all my life?",

    "Android Studio next version's name is Arctic Fox",

    "Android Studio Arctic Fox tooling for Compose is top notch ^_^",

    "I didn't know you can now run the emulator directly from Android Studio",

    "Compose Previews are great to check quickly how a composable layout looks like",

    "Previews are also interactive after enabling the experimental setting",

    "Have you tried writing build.gradle with KTS?",

    "This reminds me of Java",

    "Mutable states for recomposition remind me of javascript and react",
)



@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) {message ->
            MessageCard(message)
        }
    }
}

/*
@Preview
@Composable
fun PreviewConversation() {
    HW1Theme {
        Conversation(SampleData.conversationSample)
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    HW2Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MessageCard(
                msg = Message(
                    "Lexi",
                    "Hey, take a look at Jetpack Compose, it's great!"
                )
            )
        }
    }
}
*/

@Composable
fun MainScreen(user: UserViewModel) {
    val conversation = messageBodies.map {
        body ->
        Message(author = user, body = body)
    }
    Conversation(conversation)
}