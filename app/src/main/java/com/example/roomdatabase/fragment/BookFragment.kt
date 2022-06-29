package com.example.roomdatabase.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bai1.viewmodel.BookViewModel
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.FragmentBookBinding
import com.example.roomdatabase.model.AdapterBook

class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!

    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookBinding.inflate(inflater, container, false)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        binding.floatingAdd.setOnClickListener {
            findNavController().navigate(R.id.action_bookFragment_to_addFragment)
        }

        var adapterBook = AdapterBook()

        val recyclerBook = binding.rcBook
        recyclerBook.adapter = adapterBook
        recyclerBook.layoutManager = LinearLayoutManager(requireContext())


        mBookViewModel.readAllData.observe(viewLifecycleOwner, Observer { book ->
            adapterBook.setData(book)
        })

        deleteAllBook()
        return binding.root
    }

    fun deleteAllBook() {
        binding.imgDelete.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                mBookViewModel.deleteAllUsers()
                Toast.makeText(
                    requireContext(),
                    "Successfully removed everything",
                    Toast.LENGTH_SHORT
                )
                    .show()

            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete everything ?")
            builder.setMessage("Are you sure to remove everything ?")
            builder.create()
                .show()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}