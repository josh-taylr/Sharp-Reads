package dev.josh.taylor.sharpreads

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.josh.taylor.sharpreads.di.component.MainComponent
import dev.josh.taylor.sharpreads.ui.auth.SignInFragment
import dev.josh.taylor.sharpreads.ui.main.BookFragment
import dev.josh.taylor.sharpreads.ui.main.BookListFragment
import dev.josh.taylor.sharpreads.ui.main.BookViewModel
import dev.josh.taylor.sharpreads.ui.main.EmptyBookListFragment
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: BookViewModel by viewModels { viewModelFactory }

    private lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        mainComponent = (applicationContext as SharpReadsApplication).appComponent
            .mainComponent()
            .create()

        mainComponent.inject(this)

        supportFragmentManager.fragmentFactory = fragmentFactory

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        viewModel.navigateToBook.observe(this, Observer { event ->
            event.getContentOrNull()?.let { book ->
                showBook(book.id)
            }
        })

        viewModel.booksError.observe(this, Observer { showError ->
            if (showError) showEmptyListFragment()
        })

        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, BookListFragment::class.java, bundleOf(), "BookList")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.accountMenuItem -> {
                startLoginFlow()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    fun showBook(bookId: Int) {
        supportFragmentManager.commit {
            addToBackStack("ShowBookDetail")
            replace(
                R.id.container,
                BookFragment::class.java,
                BookFragment.createBundle(bookId),
                "BookDetail"
            )
        }
    }

    fun showEmptyListFragment() {
        supportFragmentManager.commit {
            setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            replace(R.id.container, EmptyBookListFragment::class.java, bundleOf(), null)
        }
    }

    private fun startLoginFlow() {
        supportFragmentManager.commit {
            addToBackStack("ShowSignIn")
            replace(R.id.container, SignInFragment::class.java, bundleOf(), "SignIn")
        }
    }
}
