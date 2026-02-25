package fi.gibanator.subbearandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import fi.gibanator.subbearandroid.navigation.AddSubscription
import fi.gibanator.subbearandroid.navigation.UpcomingSubscriptions
import fi.gibanator.subbearandroid.ui.main.AddSubscriptionScreen
import fi.gibanator.subbearandroid.ui.main.UpcomingSubscriptionsScreen
import fi.gibanator.subbearandroid.ui.theme.SubbearAndroidTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubbearAndroidTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Subbear") },
                            navigationIcon = {
                                if (currentDestination?.route == AddSubscription.route) {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                                    }
                                }
                            }
                        )
                    },
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Subscriptions") },
                                label = { Text("Subscriptions") },
                                selected = currentDestination?.hierarchy?.any { it.route == UpcomingSubscriptions.route } == true,
                                onClick = {
                                    navController.navigate(UpcomingSubscriptions.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = UpcomingSubscriptions.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(UpcomingSubscriptions.route) {
                            UpcomingSubscriptionsScreen(navController = navController)
                        }
                        composable(AddSubscription.route) {
                            AddSubscriptionScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}