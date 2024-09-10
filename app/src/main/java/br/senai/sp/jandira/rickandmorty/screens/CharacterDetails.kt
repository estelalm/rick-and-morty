package br.senai.sp.jandira.rickandmorty.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.rickandmorty.model.Character
import br.senai.sp.jandira.rickandmorty.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun CharacterDetails(navController: NavHostController, characterId: String?) {

    var idCharacter = characterId!!
    println(characterId)

    var character by remember{
        mutableStateOf(Character())
    }

    val callCharacter = RetrofitFactory()
        .getCharacterService()
        .getCharacterById(idCharacter.toInt())

    callCharacter.enqueue(object : Callback<Character> {
        override fun onResponse(p0: Call<Character>, response: Response<Character>) {
            if(response.code() == 404){
                character = Character()
            }else{
                character = response.body()!!
            }

        }
        override fun onFailure(p0: Call<Character>, p1: Throwable) {

        }
    })

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFA1CCD1)
    ) {

        Column (
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Spacer(modifier = Modifier.height(12.dp))
            Row (modifier = Modifier.fillMaxWidth()){
                IconButton(onClick = {
                    navController.navigate("listCharacters")
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                }
            }
            Spacer(modifier = Modifier.height(64.dp))
            Card (
                modifier = Modifier
                    .size(120.dp),
                shape = CircleShape
            ){
                AsyncImage(
                    model =
                    if(character.image == "") "https://cdn.segaamerica.com/img/fearless/about/shadow-about.png"
                    else character.image,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text =
                if(character.name == "") "Buscando personagem..."
                else "Name: " + character.name
            )
            Text(
                text = "Species: " + character.species
            )
            Text(
                text = "Status: " + character.status
            )
        }
    }
}

