package dev.josh.taylor.sharpreads.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dev.josh.taylor.sharpreads.R
import kotlinx.android.synthetic.main.fragment_book.*
import javax.inject.Inject

private const val ARG_BOOK_ID = "bookId"

class BookFragment @Inject constructor() : Fragment() {

    private val viewModel: BookViewModel by activityViewModels()

    private val bookPosition: Int
        get() {
            val position = requireArguments().getInt(ARG_BOOK_ID)
            check(position >= position) { "Argument was not set: ARG_BOOK_ID" }
            return position
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setBook(bookPosition)
        viewModel.book.observe(viewLifecycleOwner, Observer { bookTitle.text = it.title })
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPause() {
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(false)
        super.onPause()
    }

    override fun onDetach() {
        if (isRemoving) viewModel.closeBook()
        super.onDetach()
    }

    companion object {
        fun createBundle(bookId: Int) = bundleOf(ARG_BOOK_ID to bookId)
    }
}
