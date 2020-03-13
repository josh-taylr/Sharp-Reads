package dev.josh.taylor.sharpreads.ui.main

import androidx.lifecycle.*
import dev.josh.taylor.goodreadsapi.Book
import dev.josh.taylor.goodreadsapi.GoodReadsService
import dev.josh.taylor.sharpreads.architecture.Event
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

    private val _navigateToBook = MutableLiveData<Event<Book>>()
    val navigateToBook: LiveData<Event<Book>> = _navigateToBook

    fun bookItemClicked(position: Int) {
        val bookList = checkNotNull(books.value) { "setBook(Int) called when books == null." }
        _navigateToBook.value = Event(bookList[position])
    }

    fun setBook(id: Int) {
        val bookList = checkNotNull(books.value) { "setBook(Int) called when books == null." }
        _book.value = bookList.find { it.id == id }
    }

    fun closeBook() {
        _book.value = null
    }
}
