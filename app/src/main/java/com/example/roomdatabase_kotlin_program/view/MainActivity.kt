package com.example.roomdatabase_kotlin_program.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase_kotlin_program.databinding.ActivityMainBinding
import com.example.roomdatabase_kotlin_program.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var context: Context

    lateinit var strUsername: String
    lateinit var strPassword: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = activityMainBinding.root
        setContentView(view)
        context = this@MainActivity
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        activityMainBinding.btnAddLogin.setOnClickListener {
            strUsername = activityMainBinding.txtUsername.text.toString().trim()
            strPassword = activityMainBinding.txtPassword.text.toString().trim()
            if (strUsername.isEmpty()){
              activityMainBinding.txtUsername.error="Please enter the username"
            }else if (strPassword.isEmpty()){
               activityMainBinding.txtPassword.error= "Please enter the username"
            } else{
                loginViewModel.insertData(context,strUsername,strPassword)
                activityMainBinding.lblInsertResponse.text="Inserted Successfully"
            }

        }

        activityMainBinding.btnReadLogin.setOnClickListener {
            strUsername =activityMainBinding.txtUsername.text.toString().trim()
            loginViewModel.getLoginDetails(context,strUsername)!!.observe(this, Observer {
                if (it==null){
                    activityMainBinding.lblReadResponse.text="Data Not Found"
                    activityMainBinding.lblUseraname.text ="___"
                    activityMainBinding.lblPassword.text ="___"

                }else{
                    activityMainBinding.lblUseraname.text =it.Username
                    activityMainBinding.lblPassword.text =it.password
                    activityMainBinding.lblReadResponse.text ="Data Found SuccessFully"
                }
            })
        }
    }
}