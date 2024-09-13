package com.example.appcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appcompose.data.model.Screen
import com.example.appcompose.ui.home.HomeScreen
import com.example.appcompose.ui.learned.LearnedWordScreen
import com.example.appcompose.ui.theme.NavigationBar
import com.example.appcompose.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        NavigationHost(navController = navController)
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.WordList.route) {
        composable(Screen.WordList.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel)
        }
        composable(Screen.LearnedWords.route) {
            LearnedWordScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(Screen.WordList, Screen.LearnedWords)

    NavigationBar(containerColor = NavigationBar, contentColor = Color.Black) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(painterResource(screen.icon), contentDescription = null) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}
