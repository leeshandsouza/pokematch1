package com.gameguildstudios.pokematch.Retrofit;

import com.gameguildstudios.pokematch.Model.Pokedex;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IPokemonDex {
    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();
}
