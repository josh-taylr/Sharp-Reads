package dev.josh.taylor.sharpreads.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.josh.taylor.goodreadsapi.Book
import dev.josh.taylor.goodreadsapi.GoodReadsService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class BookViewModel @Inject constructor(
    private val goodReadsService: GoodReadsService
) : ViewModel() {

    private val _books = liveData(Dispatchers.IO) {
        val search = goodReadsService.search("Bill Bryson")
        emit(search.results.map { it.book })
    }
    val books: LiveData<List<Book>> = _books

    private val _isLoading = MediatorLiveData<Boolean>().apply {
        addSource(books) { value = it == null }
    }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _book = MediatorLiveData<Book>()
    val book: LiveData<Book> = _book

    fun setBook(position: Int) {
        val bookList = checkNotNull(books.value) { "setBook(Int) called when books == null." }
        _book.value = bookList[position]
    }

    fun closeBook() {
        _book.value = null
    }
}
