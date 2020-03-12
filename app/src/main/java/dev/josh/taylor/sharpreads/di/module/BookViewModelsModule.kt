package dev.josh.taylor.sharpreads.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.josh.taylor.sharpreads.architecture.di.ActivityScope
import dev.josh.taylor.sharpreads.architecture.di.InjectingViewModelFactory
import dev.josh.taylor.sharpreads.architecture.di.ViewModelKey
import dev.josh.taylor.sharpreads.ui.main.BookViewModel

@Module
interface BookViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(BookViewModel::class)
    fun bindBookViewModel(bookViewModel: BookViewModel): ViewModel

    @Binds
    @ActivityScope
    fun bindViewModuleFactory(factory: InjectingViewModelFactory): ViewModelProvider.Factory
}
