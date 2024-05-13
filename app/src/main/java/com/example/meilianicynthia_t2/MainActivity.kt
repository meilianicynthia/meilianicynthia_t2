package com.example.meilianicynthia_t2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meilianicynthia_t2.ui.theme.Meilianicynthia_t2Theme

class MainActivity : ComponentActivity() {

    companion object {
        val items = listOf(
            ListItem(1,"Film Barbie", R.drawable.barbie),
            ListItem(2,"Film Dune", R.drawable.dune),
            ListItem(3,"Film Exhuma", R.drawable.exhuma),
            ListItem(4,"Film Parasite", R.drawable.parasite),
            ListItem(5,"Film Avatar", R.drawable.avatar),
            ListItem(6,"Film Gravity", R.drawable.gravity),
            ListItem(7,"Film Curve", R.drawable.curve),
            ListItem(8,"Film KKN", R.drawable.kkn),
            ListItem(9,"Film Zootopia", R.drawable.zootopia),
            ListItem(10,"Film Mulan", R.drawable.mulan),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Meilianicynthia_t2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Bottomnav()
                }
            }
        }
    }
}

data class ListItem(val id: Int, val title: String, val image: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bottomnav() {
    val navigationController = rememberNavController()
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        topBar = {
                 val navBackStackEntry by navigationController.currentBackStackEntryAsState()
            val currentroute = navBackStackEntry?.destination?.route
            val title = when (currentroute){
                Screens.Home.screen ->"Home"
                Screens.Favorite.screen ->"Favorite"
                Screens.DetailView.screen + "/{itemid}"->"Detail"
                else ->"empty"
            }
            TopAppBar(title = { Text(text = title) },
                navigationIcon = {
                    if (currentroute == Screens.DetailView.screen + "/{itemid}"){
                        IconButton(onClick = { navigationController.popBackStack()}) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    }
                })
        },
        bottomBar = {
            BottomAppBar {
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Home
                        navigationController.navigate(Screens.Home.screen)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Home, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Home) Color.White else Color.DarkGray
                    )
                }
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Favorite
                        navigationController.navigate(Screens.Favorite.screen)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Favorite, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Favorite) Color.White else Color.DarkGray
                    )
                }
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Person
                        navigationController.navigate(Screens.Person.screen)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Person, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Person) Color.White else Color.DarkGray
                    )
                }

            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screens.Home.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.screen) { Home(navigationController) }
            composable(Screens.Favorite.screen) { Favorite_1(navigationController) }
            composable(Screens.DetailView.screen + "/{itemid}", 
                arguments = listOf(navArgument("itemid"){
                    type = NavType.IntType
                })
            ) {
                navBackStackEntry ->  
                Detailview(Itemid = navBackStackEntry.arguments?.getInt("itemid"))
            }
        }
    }
}
