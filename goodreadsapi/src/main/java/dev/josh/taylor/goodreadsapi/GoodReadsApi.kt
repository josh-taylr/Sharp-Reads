package dev.josh.taylor.goodreadsapi

import retrofit2.http.GET
import retrofit2.http.Query

internal interface GoodReadsApi {

    @GET("search/")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("search") searchField: SearchField = SearchField.all
    ) : SearchResponse
}

enum class SearchField {
    title,
    author,
    all
}