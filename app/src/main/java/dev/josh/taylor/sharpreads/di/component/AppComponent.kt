package dev.josh.taylor.sharpreads.di.component

import dagger.Component
import dev.josh.taylor.sharpreads.di.module.SubComponentsModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    SubComponentsModule::class
])
interface AppComponent {
    fun mainComponent(): MainComponent.Factory
    fun signInComponent(): SignInComponent.Factory
}