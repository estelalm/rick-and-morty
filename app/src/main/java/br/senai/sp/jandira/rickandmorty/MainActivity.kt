package br.senai.sp.jandira.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.senai.sp.jandira.rickandmorty.screens.CharacterDetails
import br.senai.sp.jandira.rickandmorty.screens.ListAllCharacters
import br.senai.sp.jandira.rickandmorty.ui.theme.RickAndMortyTheme
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                    Surface(
                        color = Color.White){
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = "listCharacters") {
                            composable(route = "listCharacters"){ ListAllCharacters(navController)}
                            composable(route = "characterDetails/{characterId}",
                                arguments = listOf(navArgument("userId") {
                                    type = NavType.StringType
                                })
                            ){ backStackEntry -> CharacterDetails(navController, backStackEntry.arguments?.getString("userId")) }
                        }

                }

                }
            }
        }
    }

