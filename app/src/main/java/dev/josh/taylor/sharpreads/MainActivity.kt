package dev.josh.taylor.sharpreads

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import dev.josh.taylor.sharpreads.di.component.MainComponent
import dev.josh.taylor.sharpreads.ui.auth.SignInFragment
import dev.josh.taylor.sharpreads.ui.main.BookFragment
import dev.josh.taylor.sharpreads.ui.main.BookListFragment
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

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

        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, BookListFragment::class.java, bundleOf(),"BookList")
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

    private fun startLoginFlow() {
        supportFragmentManager.commit {
            addToBackStack("ShowSignIn")
            replace(R.id.container, SignInFragment::class.java, bundleOf(), "SignIn")
        }
    }
}
