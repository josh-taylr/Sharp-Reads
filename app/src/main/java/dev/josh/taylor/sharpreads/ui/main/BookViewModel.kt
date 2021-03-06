package dev.josh.taylor.sharpreads.ui.main

import androidx.lifecycle.*
import dev.josh.taylor.goodreadsapi.Book
import dev.josh.taylor.goodreadsapi.GoodReadsService
import dev.josh.taylor.sharpreads.architecture.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class BookViewModel @Inject constructor(
    private val goodReadsService: GoodReadsService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _books = liveData(ioDispatcher) {
        try {
            val search = goodReadsService.search("Bill Bryson")
            emit(search.results.map { it.book })
            _booksError.postValue(false)
        } catch (e: SSLHandshakeException) {
            _booksError.postValue(true)
        }
    }
    val books: LiveData<List<Book>> = _books

    private val _booksError = MutableLiveData<Boolean>()
    val booksError: LiveData<Boolean> = _booksError

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
