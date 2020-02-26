package dev.josh.taylor.sharpreads.di.component

import dagger.Subcomponent
import dev.josh.taylor.sharpreads.MainActivity
import dev.josh.taylor.sharpreads.di.ActivityScope
import dev.josh.taylor.sharpreads.di.module.MainModule

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }
    fun inject(mainActivity: MainActivity)
}