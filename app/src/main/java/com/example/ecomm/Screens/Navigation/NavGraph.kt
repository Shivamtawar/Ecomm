package com.example.ecomm.Screens.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ecomm.Screens.CartSystem.CartScreen
import com.example.ecomm.Screens.Home.HomeScreen
import com.example.ecomm.Screens.LoginIn.LoginSCreen
import com.example.ecomm.Screens.Product.ProductDetailScreen
import com.example.ecomm.Screens.Profile.ProfileScreen
import com.example.ecomm.Screens.SignIN.SignInScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph(navController: NavHostController)
     {

    val navController = rememberNavController()
         val user = FirebaseAuth.getInstance().currentUser

         val start = if(user != null) "bottomNav" else "login"

        NavHost(
            navController = navController, startDestination = start

    ) {
        composable("login"){
            LoginSCreen(navController)
        }
           composable("Signup"){
                 SignInScreen(navController)
        }
        composable("bottomNav"){
            BottomNav(navController)
        }
            composable("Home"){
                HomeScreen(navController)
            }
            composable("Profile"){
                ProfileScreen(navController)
            }
            composable("cart"){
                CartScreen()
            }

            composable("product/{name}/{description}/{price}",
                arguments = listOf(
                    navArgument("name") {type = NavType.StringType},
                    navArgument("description") {type = NavType.StringType},
                    navArgument("price") {type = NavType.StringType}

                )
            ){ navBackStackEntry ->
                ProductDetailScreen(
                    navController,
                    productName = navBackStackEntry.arguments?.getString("name") ?: "",
                    productDescription = navBackStackEntry.arguments?.getString("description") ?: "",
                    productPrice = navBackStackEntry.arguments?.getString("price") ?: ""

                )
                
            }

            //composable("searchBar"){
             //   HomeScreen(navController)
            //}
    }
    
}
