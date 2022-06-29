package com.example.roomdatabase.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bai1.model.Book
import com.example.roomdatabase.R
import com.example.roomdatabase.fragment.BookFragmentDirections
import java.util.ArrayList

class AdapterBook : RecyclerView.Adapter<AdapterBook.ViewHolder>() {
    private var mListBook = emptyList<Book>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var book = mListBook[position]
        holder.itemView.findViewById<TextView>(R.id.text_name_book).text = book.name

        holder.itemView.findViewById<LinearLayout>(R.id.layout_book).setOnClickListener {
            val action =
                BookFragmentDirections.actionBookFragmentToUpdateFragment(book) // <- Pass object to Update Fragment
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return mListBook.size
    }

    fun setData(book: List<Book>) {
        this.mListBook = book
        notifyDataSetChanged()
    }
}