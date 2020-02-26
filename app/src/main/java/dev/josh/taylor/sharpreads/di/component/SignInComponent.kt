package dev.josh.taylor.sharpreads.di.component

import dagger.Subcomponent
import dev.josh.taylor.sharpreads.ui.auth.SignInFragment

@Subcomponent
interface SignInComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SignInComponent
    }
    fun inject(signInFragment: SignInFragment)
}
