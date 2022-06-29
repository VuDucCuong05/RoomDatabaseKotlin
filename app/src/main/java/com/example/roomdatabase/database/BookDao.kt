package com.example.bai1.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bai1.model.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // <- Annotate the 'addUser' function below. Set the onConflict strategy to IGNORE so if exactly the same user exists, it will just ignore it.
    fun addBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Delete
     fun deleteBook(book: Book)

    @Query("DELETE FROM book")
    fun deleteAllBook()

    @Query("SELECT * from book ORDER BY id ASC") // <- Add a query to fetch all users (in user_table) in ascending order by their IDs.
    fun readAllData(): LiveData<List<Book>> // <- This means function return type is List. Specifically, a List of Users.

}