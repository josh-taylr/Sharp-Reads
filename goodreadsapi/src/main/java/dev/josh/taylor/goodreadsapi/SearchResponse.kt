package dev.josh.taylor.goodreadsapi

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "GoodreadsResponse", strict = false)
internal class SearchResponse {

    @field:Element(required = false)
    lateinit var search: Search
}

class Search {

    @Suppress("unused")
    internal constructor()

    constructor(start: Int, end: Int, totalCount: Int, results: MutableList<Work>) {
        this.start = start
        this.end = end
        this.totalCount = totalCount
        this.results = results
    }

    @field:Element(name = "results-start", required = false)
    var start: Int = 0
        internal set

    @field:Element(name = "results-end", required = false)
    var end: Int = 1
        internal set

    @field:Element(name = "total-results", required = false)
    var totalCount: Int = 0
        internal set

    @field:ElementList(required = false)
    lateinit var results: MutableList<Work>
        internal set
}

class Work {

    @Suppress("unused")
    internal constructor()

    constructor(id: Int, book: Book) {
        this.id = id
        this.book = book
    }

    @field:Element(required = false)
    var id: Int = -1
        internal set

    @field:Element(name = "best_book")
    lateinit var book: Book
        internal set
}

class Book {

    @Suppress("unused")
    internal constructor()

    constructor(id: Int, title: String) {
        this.id = id
        this.title = title
    }

    @field:Element(required = false)
    var id: Int = -1
        internal set

    @field:Element(required = false)
    lateinit var title: String
        internal set

    override fun equals(other: Any?) =
        when (other) {
            is Book -> this.id == other.id
            else -> false
        }

    override fun hashCode() = id
}
