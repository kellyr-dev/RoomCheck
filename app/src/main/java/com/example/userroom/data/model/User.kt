package com.example.userroom.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import java.io.Serializable


@Entity(tableName = "user_tb")
data class User (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName : String,
    val lastName : String,
    val email : String,
    val age: Int,

): Serializable