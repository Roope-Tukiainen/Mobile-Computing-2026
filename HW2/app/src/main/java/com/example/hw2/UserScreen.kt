package com.example.hw2

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hw2.ui.theme.HW2Theme
import kotlinx.serialization.Serializable


@Serializable
//profileImageId: R.drawable.profileImage
data class UserInfo(val profileImageId: Int, val name: String)
@Composable
fun UserInfoScreen(user: UserInfo) {
    Column(modifier = Modifier.padding(all=8.dp)) {
        Text(
            text = "User info:",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.width(8.dp))

        Image (
            painter = painterResource(user.profileImageId),
            contentDescription = "Family hamburger picture",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Name: ${user.name}",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun PreviewUserInfoScreen() {
    HW2Theme {
        UserInfoScreen(user = UserInfo(R.drawable.hamburger, "Developer"))
    }
}