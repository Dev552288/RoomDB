package com.example.roomdatabase_kotlin_program.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabase_kotlin_program.model.LoginTableModel

@Database(entities = arrayOf(LoginTableModel::class), version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {
    abstract fun loginDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: LoginDatabase? = null
        fun getDataBaseClient(context: Context): LoginDatabase {
            if (INSTANCE != null) {
                return INSTANCE!!
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(context, LoginDatabase::class.java, "room_db")
                    .fallbackToDestructiveMigration()
                    .build()
                return INSTANCE!!
            }
        }
    }
}