package dev.josh.taylor.sharpreads.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.josh.taylor.sharpreads.R

class SignInViewModel : ViewModel() {
    private val _message = MutableLiveData(R.string.sign_in_message)
    val message: LiveData<Int> = _message
}
