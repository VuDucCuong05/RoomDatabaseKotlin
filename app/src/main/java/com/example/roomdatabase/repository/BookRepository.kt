package com.example.bai1.repository

import androidx.lifecycle.LiveData
import com.example.bai1.database.BookDao
import com.example.bai1.model.Book

class BookRepository(private val bookDao: BookDao) {
    val readAllData: LiveData<List<Book>> = bookDao.readAllData()

     fun addBook(book: Book) {
        return bookDao.addBook(book)
    }

     fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }

     fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }

     fun deleteAllBook() {
        bookDao.deleteAllBook()
    }
}