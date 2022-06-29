package com.example.bai1.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Book")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val image: Int,
    val content: String
) : Parcelable

