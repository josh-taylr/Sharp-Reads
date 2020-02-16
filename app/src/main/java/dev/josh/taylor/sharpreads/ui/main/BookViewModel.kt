package dev.josh.taylor.sharpreads.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {

    private val data = listOf(
        "Brave New World",
        "Sapiens: A Brief History of Human Kind",
        "Hooked: How to Build Habit-Forming Products",
        "Thus Spoke Zarathustra",
        "Beyond Good and Evil",
        "Capitalism Without Capital",
        "The Windup Bird Chronicles",
        "The Happiness Hypothesis",
        "The Road",
        "Do Androids Dream of Electric Sheep",
        "Fooled By Randomness"
    )

    private val _books = MutableLiveData(data)
    val books: LiveData<List<String>> = _books

    private val _isLoading = MediatorLiveData<Boolean>().apply {
        addSource(books) { value = it == null }
    }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _bookTitle = MediatorLiveData<String>()
    val bookTitle: LiveData<String> = _bookTitle

    fun setBook(position: Int?) {
        _bookTitle.value = position?.let { data[it] }
    }

    fun closeBook() {
        _bookTitle.value = null
    }
}
