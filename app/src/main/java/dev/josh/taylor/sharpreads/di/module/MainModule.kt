package dev.josh.taylor.sharpreads.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.josh.taylor.sharpreads.di.ActivityScope
import dev.josh.taylor.sharpreads.di.FragmentKey
import dev.josh.taylor.sharpreads.ui.auth.SignInFragment
import dev.josh.taylor.sharpreads.ui.main.BookFragment
import dev.josh.taylor.sharpreads.ui.main.BookListFragment
import dev.josh.taylor.sharpreads.ui.main.InjectingFragmentFactory

@Module(includes = [Declarations::class])
class MainModule

@Module
private interface Declarations {

    @Binds
    @IntoMap
    @FragmentKey(BookListFragment::class)
    fun bindBookListFragment(bookListFragment: BookListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(BookFragment::class)
    fun bindBookFragment(bookFragment: BookFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SignInFragment::class)
    fun bindSignInFragment(signInFragment: SignInFragment): Fragment

    @Binds
    @ActivityScope
    fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}