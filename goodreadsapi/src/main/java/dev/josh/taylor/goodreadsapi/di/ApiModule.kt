package dev.josh.taylor.goodreadsapi.di

import dagger.Module
import dagger.Provides
import dev.josh.taylor.goodreadsapi.GoodReadsApi
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    internal fun providesGoodReadsApi(retrofit: Retrofit) =
        retrofit.create(GoodReadsApi::class.java)
}
