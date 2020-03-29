package dev.josh.taylor.sharpreads.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.josh.taylor.goodreadsapi.Book
import dev.josh.taylor.goodreadsapi.GoodReadsService
import dev.josh.taylor.goodreadsapi.Search
import dev.josh.taylor.goodreadsapi.Work
import dev.josh.taylor.sharpreads.test.MainCoroutineRule
import dev.josh.taylor.sharpreads.test.getOrAwaitValue
import dev.josh.taylor.sharpreads.test.runBlocking
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.junit.MockitoJUnitRunner
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
    lateinit var goodReadsService: GoodReadsService

    private lateinit var viewModel: BookViewModel

    @Before
    fun setup() {
        viewModel = BookViewModel(goodReadsService, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun fetchBooks() = mainCoroutineRule.runBlocking {
        // given
        val book = Book(101, "Fooled by Randomness")
        val search = Search(
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
        whenever(goodReadsService.search(anyString())).thenReturn(search)

        // when books are observed
        val books = viewModel.books.getOrAwaitValue()

        // then
        assertEquals(listOf(book), books)
    }
}
