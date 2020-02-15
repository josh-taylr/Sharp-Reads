package dev.josh.taylor.sharpreads

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.josh.taylor.sharpreads.ui.main.BookListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BookListFragment.newInstance())
                    .commitNow()
        }
    }
}
