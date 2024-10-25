package com.example.roomdatabase_kotlin_program.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.roomdatabase_kotlin_program.model.LoginTableModel
import com.example.roomdatabase_kotlin_program.room.LoginDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRepository {
    companion object {
        var loginDatabase: LoginDatabase? = null
        var loginTableModel: LiveData<LoginTableModel>? = null
        fun initialdb(context:Context): LoginDatabase {
            return LoginDatabase.getDataBaseClient(context)
        }
        fun insertData(context:Context,username:String,password:String){
            loginDatabase = initialdb(context)
            CoroutineScope(Dispatchers.IO).launch {
                var loginDetails =LoginTableModel(username,password)
                loginDatabase!!.loginDao().InsertData(loginDetails)
            }

        }

        fun getLoginDetails(context: Context, username: String) : LiveData<LoginTableModel>? {

            loginDatabase = initialdb(context)

            loginTableModel = loginDatabase!!.loginDao().getLoginDetails(username)

            return loginTableModel
        }
    }
}