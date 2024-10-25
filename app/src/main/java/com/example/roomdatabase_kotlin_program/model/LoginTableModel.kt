package com.example.roomdatabase_kotlin_program.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.PasswordAuthentication

@Entity(tableName = "login")
data class LoginTableModel(
    @ColumnInfo(name = "username")
    var Username: String,
    @ColumnInfo(name = "password")
    var password: String,
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}
