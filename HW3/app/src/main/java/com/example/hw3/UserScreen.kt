package com.example.hw3

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.hw3.ui.theme.HW3Theme


@Composable
fun ProfileImage(user: UserViewModel) {
    val context = LocalContext.current
    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(Uri.parse(user.avatarUri)) }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) {
        uri: Uri? -> if (uri != null) {
            // Take persisting permission so that image can be loaded even after restart
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            user.updateAvatar(uri.toString())
            selectedImageUri = uri
        }
    }

    Image (
        painter = rememberAsyncImagePainter(selectedImageUri?: drawableToUri(R.drawable.hamburger)),
        contentDescription = "Profile image",
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .clickable {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }
    )
}

@Composable
fun UserInfoScreen(user: UserViewModel) {

    Column(modifier = Modifier.padding(all=8.dp)) {
        Text(
            text = "User info:",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.width(8.dp))

        ProfileImage(user)

        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Name: ${user.name}",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.width(8.dp))

        var newName by rememberSaveable {mutableStateOf("")}
        InputField(
            newName,
            {
                newName = it
                user.updateName(it)
            },
            "Change name:"
        )
    }
}


@Preview
@Composable
fun PreviewUserInfoScreen() {
    val previewUser = UserViewModel(PreferenceDataStore(LocalContext.current))
    HW3Theme {
        UserInfoScreen(previewUser)
    }
}

