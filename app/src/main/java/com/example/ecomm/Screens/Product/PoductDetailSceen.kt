package com.example.ecomm.Screens.Product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
    productName: String = "Sample Product",
    productDescription: String = "This is a great product that helps students manage their lifestyle efficiently.",
    productPrice: String = "₹499"
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Product Image Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = Icons.Default.Image,
                    contentDescription = "Product Image",
                    modifier = Modifier.size(120.dp),
                )
            }

            Text(
                text = productName,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = productDescription,
                fontSize = 16.sp,
                lineHeight = 22.sp
            )

            Text(
                text = "Price: $productPrice",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* TODO: Add to Cart */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Add to Cart")
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = { /* TODO: Buy Now */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Buy Now")
                }
            }
        }
    }
}
