package dev.josh.taylor.goodreadsapi.di

import dagger.Module
import dagger.Provides
import dev.josh.taylor.goodreadsapi.goodReadsInterceptor
import okhttp3.OkHttpClient
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideTrustedClient(key: String): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(goodReadsInterceptor(key))
            .build()

    @Provides
    fun provideRestAdapter(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.goodreads.com")
            .client(client)
            .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                Persister(AnnotationStrategy())
            ))
            .build()
}
