package dev.josh.taylor.goodreadsapi

import dev.josh.taylor.goodreadsapi.di.DaggerLibraryComponent
import javax.inject.Inject

@Suppress("MemberVisibilityCanBePrivate")
class GoodReadsService(key: String) {

    @Inject
    internal lateinit var goodReadsApi: GoodReadsApi

    init {
        DaggerLibraryComponent.builder()
            .key(key)
            .create()
            .inject(this)
    }

    suspend fun search(query: String): Search = goodReadsApi.search(query).search
}