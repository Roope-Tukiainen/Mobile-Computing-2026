package com.example.hw3

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.example.hw3.ui.theme.HW3Theme


@Composable
fun InputField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(label, color = MaterialTheme.colorScheme.secondary)},
        singleLine = true
    )
}

@Preview
@Composable
fun PreviewInputField() {
    var text by rememberSaveable {mutableStateOf("")}
    HW3Theme {
        InputField(text, {text = it}, "Test: ")
    }
}