package dev.josh.taylor.goodreadsapi

import okhttp3.Interceptor

internal fun goodReadsInterceptor(key: String) = Interceptor { chain ->

    val originalRequest = chain.request()
    val originalUrl = originalRequest.url()

    val url = originalUrl.newBuilder()
        .addQueryParameter("key", key)
        .build()

    val request = originalRequest.newBuilder()
        .url(url)
        .build()

    chain.proceed(request)
}