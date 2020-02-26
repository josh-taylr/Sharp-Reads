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

    @field:Element(required = false)
    var id: Int = -1
        internal set

    @field:Element(name = "best_book")
    lateinit var book: Book
        internal set
}

class Book {

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
