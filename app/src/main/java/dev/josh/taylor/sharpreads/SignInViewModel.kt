package dev.josh.taylor.sharpreads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    private val _message = MutableLiveData(R.string.sign_in_message)
    val message: LiveData<Int> = _message
}
