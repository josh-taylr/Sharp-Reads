package dev.josh.taylor.sharpreads.di.module

import dagger.Module
import dev.josh.taylor.sharpreads.di.component.MainComponent
import dev.josh.taylor.sharpreads.di.component.SignInComponent

@Module(
    subcomponents = [
        MainComponent::class,
        SignInComponent::class
    ]
)
class SubComponentsModule