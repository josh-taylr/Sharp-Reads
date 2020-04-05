package dev.josh.taylor.sharpreads.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dev.josh.taylor.goodreadsapi.Book
import dev.josh.taylor.goodreadsapi.GoodReadsService
import dev.josh.taylor.goodreadsapi.Search
import dev.josh.taylor.goodreadsapi.Work
import dev.josh.taylor.sharpreads.architecture.Event
import dev.josh.taylor.sharpreads.test.MainCoroutineRule
import dev.josh.taylor.sharpreads.test.getOrAwaitValue
import dev.josh.taylor.sharpreads.test.runBlocking
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import javax.net.ssl.SSLHandshakeException
import org.mockito.Mockito.`when` as whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BookViewModelTest {

    @Rule
    @JvmField
    val taskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var booksErrorObserver: Observer<Boolean>

    @Mock
    lateinit var bookObserver: Observer<Book>

    @Mock
    lateinit var navigateToBookObserver: Observer<Event<Book>>

    @Mock
    lateinit var goodReadsService: GoodReadsService

    private lateinit var viewModel: BookViewModel

    private val bookId = 101
    private val book = Book(bookId, "Fooled by Randomness")
    private val search = Search(
        start = 0,
        end = 1,
        totalCount = 1,
        results = mutableListOf(
            Work(
                id = 1,
                book = book
            )
        )
    )
    private val emptySearch = Search(
        start = 0,
        end = 0,
        totalCount = 0,
        results = mutableListOf()
    )

    @Before
    fun setup() {
        viewModel = BookViewModel(goodReadsService, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun fetchBooks() = mainCoroutineRule.runBlocking {
        // given
        whenever(goodReadsService.search(anyString())).thenReturn(search)

        // when books are observed
        val books = viewModel.books.getOrAwaitValue()

        // then
        assertEquals(listOf(book), books)
    }

    @Test
    fun fetchEmptyBooks() = mainCoroutineRule.runBlocking {
        // given
        whenever(goodReadsService.search(anyString())).thenReturn(emptySearch)

        // when books are observed
        val books = viewModel.books.getOrAwaitValue()

        // then
        assertEquals(emptyList<Book>(), books)
    }

    @Test
    fun fetchBooksErrors() = mainCoroutineRule.runBlocking {
        // given
        whenever(goodReadsService.search(anyString())).thenAnswer {
            throw SSLHandshakeException("Fake SSL hand shake error.")
        }
        viewModel.booksError.observeForever(booksErrorObserver)

        // when
        viewModel.books.observeForever { }

        // then
        verify(booksErrorObserver).onChanged(true)
    }

    @Test
    fun loadingState() = mainCoroutineRule.runBlocking {
        // given
        whenever(goodReadsService.search(anyString())).thenReturn(search)

        // when
        val isLoading = viewModel.isLoading.getOrAwaitValue()

        // then
        assertFalse(isLoading)
    }

    @Test
    fun navigateToBook() = mainCoroutineRule.runBlocking {
        // given
        whenever(goodReadsService.search(anyString())).thenReturn(search)
        viewModel.navigateToBook.observeForever(navigateToBookObserver)
        viewModel.books.getOrAwaitValue()

        // when
        viewModel.bookItemClicked(0)

        // then
        verify(navigateToBookObserver).onChanged(Event(book))
    }

    @Test
    fun setBook() = mainCoroutineRule.runBlocking {
        // given
        whenever(goodReadsService.search(anyString())).thenReturn(search)
        viewModel.book.observeForever(bookObserver)
        viewModel.books.getOrAwaitValue()

        // when
        viewModel.setBook(bookId)

        // then
        verify(bookObserver).onChanged(book)
    }

    @Test
    fun closeBook() = mainCoroutineRule.runBlocking {
        // given

        whenever(goodReadsService.search(anyString())).thenReturn(search)
        viewModel.book.observeForever(bookObserver)
        viewModel.books.getOrAwaitValue()
        viewModel.setBook(bookId)

        // when
        viewModel.closeBook()

        // then
        verify(bookObserver).onChanged(null)
    }
}
