package com.example.roomdatabase.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bai1.model.Book
import com.example.bai1.viewmodel.BookViewModel
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        showBook()
        updateBook()
        deleteBook()
        return binding.root
    }

    fun showBook() {
        binding.edtName.setText(args.bookUpdate.name)
        binding.edtContent.setText(args.bookUpdate.content)
    }

    fun updateBook() {
        binding.btUpdate.setOnClickListener {
            var strName = binding.edtName.text.toString()
            var strContent = binding.edtContent.text.toString()
            if (!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strContent)) {
                val bookUpdate = Book(args.bookUpdate.id, strName, R.drawable.ic_book, strContent)
                mBookViewModel.updateBook(bookUpdate)
                Toast.makeText(requireContext(), "Updated Successfully !", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_updateFragment_to_bookFragment)
            } else {
                Toast.makeText(requireContext(), "Please fill all fields !", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    fun deleteBook() {
        binding.imgDelete.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                mBookViewModel.deleteBook(args.bookUpdate)
                findNavController().navigate(R.id.action_updateFragment_to_bookFragment)
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${args.bookUpdate.name} ?")
            builder.setMessage("Are you sure to remove ${args.bookUpdate.name} ?")
            builder.create().show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}