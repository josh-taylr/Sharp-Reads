package dev.josh.taylor.sharpreads

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import dev.josh.taylor.sharpreads.ui.auth.SignInFragment
import dev.josh.taylor.sharpreads.ui.main.BookFragment
import dev.josh.taylor.sharpreads.ui.main.BookListFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, BookListFragment.newInstance(), "BookList")
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
            replace(R.id.container, BookFragment.newInstance(bookId), "BookDetail")
        }
    }

    private fun startLoginFlow() {
        supportFragmentManager.commit {
            addToBackStack("ShowSignIn")
            replace(R.id.container, SignInFragment.newInstance(), "SignIn")
        }
    }
}
