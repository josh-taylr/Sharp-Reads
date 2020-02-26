package dev.josh.taylor.sharpreads.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class InjectingViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val creator = creators[modelClass] ?: return createViewModelAsFallback(modelClass)

        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun <T : ViewModel?> createViewModelAsFallback(modelClass: Class<T>): T {
        Log.w("InjectingViewModel", "No creator found for class: $modelClass. Using default constructor")
        return modelClass.newInstance()
    }
}