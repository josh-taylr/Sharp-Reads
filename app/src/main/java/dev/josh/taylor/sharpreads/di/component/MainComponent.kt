package dev.josh.taylor.sharpreads.di.component

import dagger.Subcomponent
import dev.josh.taylor.sharpreads.MainActivity
import dev.josh.taylor.sharpreads.di.ActivityScope
import dev.josh.taylor.sharpreads.di.module.BookFragmentsModule
import dev.josh.taylor.sharpreads.di.module.BookViewModelsModule
import dev.josh.taylor.sharpreads.di.module.ServiceModule

@ActivityScope
@Subcomponent(
    modules = [
        BookFragmentsModule::class,
        BookViewModelsModule::class,
        ServiceModule::class
    ]
)
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }
    fun inject(mainActivity: MainActivity)
}