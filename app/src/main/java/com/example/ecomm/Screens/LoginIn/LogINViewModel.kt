package com.example.ecomm.Screens.LoginIn

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecomm.Models.Users
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LogINViewModel @Inject constructor() : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val _loginState = MutableStateFlow<String?>(null)
    val loginstate : StateFlow<String?> = _loginState

    fun Login(users: Users, navController: NavController){
        auth.signInWithEmailAndPassword(users.emailid,users.password).addOnCompleteListener {task ->
                if (task.isSuccessful){
                    _loginState.value =  "Login Success"
                    navController.navigate("bottomNav")

                }else{
                    _loginState.value = task.exception?.message
                }
        }
    }
}