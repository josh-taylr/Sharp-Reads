package dev.josh.taylor.goodreadsapi.di

import dagger.BindsInstance
import dagger.Component
import dev.josh.taylor.goodreadsapi.GoodReadsService
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ApiModule::class
    ]
)
interface LibraryComponent {
    @Component.Builder
    interface Builder {

        fun create(): LibraryComponent

        @BindsInstance
        fun key(key: String): Builder
    }

    fun inject(goodReadsService: GoodReadsService)
}