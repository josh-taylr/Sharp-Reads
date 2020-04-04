package dev.josh.taylor.sharpreads.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.josh.taylor.sharpreads.R
import javax.inject.Inject

class EmptyBookListFragment @Inject constructor(): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_empty_list_fragment, container, false)
}
