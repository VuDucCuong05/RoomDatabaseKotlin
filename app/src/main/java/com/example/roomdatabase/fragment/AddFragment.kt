package com.example.roomdatabase.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bai1.model.Book
import com.example.bai1.viewmodel.BookViewModel
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.FragmentAddBinding

class AddFragment : Fragment() {


    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        binding.btUpdate.setOnClickListener {
            addBook()
        }

        return binding.root
    }

    fun addBook() {
        val strName = binding.edtName.text.toString()
        val strContent = binding.edtContent.text.toString()

        if (!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strContent)) {
            val book = Book(0, strName, R.drawable.ic_book, strContent)
            mBookViewModel.addBook(book)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_bookFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}