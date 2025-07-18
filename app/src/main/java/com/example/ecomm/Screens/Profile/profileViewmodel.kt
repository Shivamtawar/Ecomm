package com.example.ecomm.Screens.Profile

import androidx.lifecycle.ViewModel
import com.example.ecomm.Models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewmodel @Inject constructor() : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val dbRef = FirebaseDatabase.getInstance().getReference("users")

    private val _userData = MutableStateFlow<Users?>(null)
    val userData = _userData.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        _loading.value = true
        val uid = auth.currentUser?.uid
        if (uid != null) {
            dbRef.child(uid).get().addOnSuccessListener {
                val user = it.getValue(Users::class.java)
                _userData.value = user
                _loading.value = false
            }.addOnFailureListener {
                _loading.value = false
            }
        } else {
            _loading.value = false
        }
    }

    fun logout() {
        auth.signOut()
        _userData.value = null
    }
}
