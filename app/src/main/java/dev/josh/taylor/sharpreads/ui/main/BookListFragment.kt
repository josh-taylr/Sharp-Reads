package dev.josh.taylor.sharpreads.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.josh.taylor.sharpreads.MainActivity
import dev.josh.taylor.sharpreads.R
import kotlinx.android.synthetic.main.book_list_fragment.*

class BookListFragment : Fragment() {

    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.book_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = BookListAdapter()
        adapter.onClick = { (requireActivity() as MainActivity).showBook(it) }

        bookList.layoutManager = LinearLayoutManager(requireContext())
        bookList.adapter = adapter

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { progressBar.isVisible = it })
        viewModel.books.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
    }

    companion object {
        fun newInstance() = BookListFragment()
    }
}

private class BookListAdapter : ListAdapter<String, BookViewHolder>(BookDiffUtil) {

    var onClick: (position: Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookViewHolder.create(parent)

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position), onClick = { onClick(it) })
    }
}

private object BookDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}

private class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val title = itemView.findViewById<AppCompatTextView>(android.R.id.text1)

    fun bind(bookTitle: String, onClick: (position: Int) -> Unit) {
        title.text = bookTitle
        itemView.setOnClickListener { onClick(adapterPosition) }
    }

    companion object {
        fun create(viewGroup: ViewGroup) =
            BookViewHolder(
                LayoutInflater.from(viewGroup.context)
                    .inflate(android.R.layout.simple_list_item_1, viewGroup, false)
            )
    }
}
