package dev.josh.taylor.sharpreads.architecture

import java.util.*

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentOrNull(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content

    override fun equals(other: Any?) = when (other) {
        is Event<*> -> this.content == other.content
        else -> false
    }

    override fun hashCode() = Objects.hash("Event", content)

    override fun toString() = "Event[$content]"
}
