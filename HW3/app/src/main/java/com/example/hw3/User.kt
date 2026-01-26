package com.example.hw3

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/***
 * User class that supports recompose on change and also updates the dataStore
 */
class UserViewModel(private val dS: PreferenceDataStore) : ViewModel() {

    var name by mutableStateOf("default Mark")
        private set
    var avatarUri by mutableStateOf(drawableToUri(R.drawable.hamburger).toString())
        private set

    init {
        viewModelScope.launch {
            dS.userGetName().collect {name = it ?: name}
        }
        viewModelScope.launch {
            dS.userGetAvatar().collect {avatarUri = it?: avatarUri}
        }
    }
    fun updateName(newName: String) {
        name = newName
        viewModelScope.launch {
            dS.userSetName(newName)
        }
    }
    fun updateAvatar(newAvatarUri: String) {
        avatarUri = newAvatarUri
        viewModelScope.launch {
            dS.userSetAvatar(newAvatarUri)
        }
    }
}