package com.example.ecomm.Screens.Profile

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val viewmodel: ProfileViewmodel = hiltViewModel()
    val users by viewmodel.userData.collectAsState()
    val loading by viewmodel.loading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Profile") }
            )
        }
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {
            when {
                loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                users != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // ✅ Display Profile Image
                        if (users!!.profileImage.isNotEmpty()) {
                            val byteArray = Base64.decode(users!!.profileImage, Base64.DEFAULT)
                            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "Profile Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        ProfileItem(label = "Name", value = users!!.username ?: "no name")
                        Spacer(modifier = Modifier.height(16.dp))

                        ProfileItem(label = "Email", value = users!!.emailid ?: "no email")
                        Spacer(modifier = Modifier.height(16.dp))

                        ProfileItem(label = "Mobile", value = users!!.phoneNumber ?: "no number")
                        Spacer(modifier = Modifier.height(32.dp))

                        Button(
                            onClick = { viewmodel.logout() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text("Logout", color = MaterialTheme.colorScheme.onError)
                        }
                    }
                }

                else -> {
                    Text("User data not found", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
