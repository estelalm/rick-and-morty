package br.senai.sp.jandira.rickandmorty.service

import br.senai.sp.jandira.rickandmorty.model.Character
import br.senai.sp.jandira.rickandmorty.model.Info
import br.senai.sp.jandira.rickandmorty.model.Result
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CharacterService {

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<Character>
    //passa o id para substituir a part "id" no path "character/{id}
    //Call - retorna a chamada que devolve um personagem

    @GET("character")
    fun getAllCharacters(): Call<Result>

    @POST("character")
    fun saveCharacter(@Body character: Character): Call<Character>
}