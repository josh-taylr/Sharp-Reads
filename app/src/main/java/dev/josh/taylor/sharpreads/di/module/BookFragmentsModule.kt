package dev.josh.taylor.sharpreads.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.josh.taylor.sharpreads.architecture.di.ActivityScope
import dev.josh.taylor.sharpreads.architecture.di.FragmentKey
import dev.josh.taylor.sharpreads.architecture.di.InjectingFragmentFactory
import dev.josh.taylor.sharpreads.ui.auth.SignInFragment
import dev.josh.taylor.sharpreads.ui.main.BookFragment
import dev.josh.taylor.sharpreads.ui.main.BookListFragment
import dev.josh.taylor.sharpreads.ui.main.EmptyBookListFragment

@Module(includes = [Declarations::class])
class BookFragmentsModule

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
    @IntoMap
    @FragmentKey(EmptyBookListFragment::class)
    fun bindEmptyBookListFragment(emptyBookListFragment: EmptyBookListFragment): Fragment

    @Binds
    @ActivityScope
    fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}
