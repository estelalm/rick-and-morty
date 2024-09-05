package br.senai.sp.jandira.rickandmorty.screens

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.rickandmorty.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import br.senai.sp.jandira.rickandmorty.model.Character
import br.senai.sp.jandira.rickandmorty.model.Result


@Composable
fun ListAllCharacters(navController: NavHostController) {

    var characterList by remember{
        mutableStateOf(listOf<Character>())
    }

    val callCharacterList = RetrofitFactory()
        .getCharacterService()
        .getAllCharacters()

    callCharacterList.enqueue(
        object : Callback<Result>{
            override fun onResponse(p0: Call<Result>, response: Response<Result>) {
                    characterList = response.body()!!.results
            }
            override fun onFailure(p0: Call<Result>, p1: Throwable) {
            }
        }


    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFA1CCD1)
    ){
        Column(modifier = Modifier.padding(8.dp)){
            Text(
                text = "Rick & Morty API",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            LazyColumn {
                items(characterList){ character ->
                    CharacterCard(character, navController)
                }

            }
        }

    }
}

@Composable
fun CharacterCard(character : Character, navController: NavHostController) {
    Card(modifier = Modifier
        .padding(bottom = 4.dp)
        .fillMaxWidth()
        .height(100.dp)
        .clickable {
      //      navController.navigate("characterDetails/${character.id}")
            navController.navigate("characterDetails/userId")
        },
        colors = CardDefaults.cardColors(containerColor = Color(0xE8FFFFFF)),
        border = BorderStroke(2.dp, Color(0xFFC5205A))

    ){

        Row{
            Card(modifier = Modifier.size(100.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Red
                )
            ){
                AsyncImage(model = character.image, contentDescription = "")
            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp), verticalArrangement = Arrangement.Center){
                Text(
                    text = character.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = character.species
                )
            }
        }



    }
}

