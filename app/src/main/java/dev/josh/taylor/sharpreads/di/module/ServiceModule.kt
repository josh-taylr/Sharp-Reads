package dev.josh.taylor.sharpreads.di.module

import dagger.Module
import dagger.Provides
import dev.josh.taylor.goodreadsapi.GoodReadsService
import dev.josh.taylor.sharpreads.BuildConfig
import dev.josh.taylor.sharpreads.di.ActivityScope

@Module
class ServiceModule {

    @ActivityScope
    @Provides
    fun provideGoodReadsService(): GoodReadsService = GoodReadsService(BuildConfig.GoodReadsKey)
}
