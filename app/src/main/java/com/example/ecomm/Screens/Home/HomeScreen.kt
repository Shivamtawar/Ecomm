package com.example.ecomm.Screens.Home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecomm.Models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val viewmodl : HomeScreenViewModel = hiltViewModel()
    val products by viewmodl.product.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Ecomm",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = {
                        val product = Product(
                            id = "",
                            productName = "laptop",
                            description = "A good Laptop",
                            price = "19999"
                        )
                        viewmodl.addProduct(product)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "",tint = Color.White )
                    }
                    IconButton(onClick = {
                        navController.navigate("cart")
                    }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "", tint = Color.White)
                    }
                },
                modifier = Modifier
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF6A11CB), Color(0xFF2575FC))
                        )
                    ),
            )
        },

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            SearchBar()
            Spacer(Modifier.height(16.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(products) { product ->
                    ProductCrad(product) {
                        navController.navigate(
                            "product/${product.productName}/${product.description}/${product.price}"
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun ProductCrad(
    product: Product,
    onClick : () -> Unit
) {
    Card(
        Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            Text(product.productName, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(product.price, color = Color.White)

        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = { searchText = it },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White, shape = RoundedCornerShape(14.dp)),
        placeholder = {
            Text("Search for products...", color = Color.Gray)
        },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFF0F0F0),
            focusedBorderColor = Color(0xFF6A11CB),
            unfocusedBorderColor = Color(0xFFE0E0E0),
            cursorColor = Color(0xFF6A11CB)
        )
    )
}
