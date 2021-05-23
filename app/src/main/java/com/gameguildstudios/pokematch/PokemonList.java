package com.gameguildstudios.pokematch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gameguildstudios.pokematch.Adapter.PokemonListAdapter;
import com.gameguildstudios.pokematch.Common.Common;
import com.gameguildstudios.pokematch.Common.ItemOffsetDecoration;
import com.gameguildstudios.pokematch.Model.Pokedex;
import com.gameguildstudios.pokematch.Retrofit.IPokemonDex;
import com.gameguildstudios.pokematch.Retrofit.RetrofitClient;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;



/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class PokemonList extends Fragment {

    private static PokemonList instance;
    private IPokemonDex iPokemonDex;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerView pokemon_list_recyclerview;

    public PokemonList() {
        Retrofit retrofit = RetrofitClient.getInstance();
        iPokemonDex = retrofit.create(IPokemonDex.class);
    }

    public static PokemonList getInstance(){
        if(instance == null){
            instance = new PokemonList();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        pokemon_list_recyclerview = (RecyclerView) view.findViewById(R.id.pokemon_list_recyclerview);
        pokemon_list_recyclerview.setHasFixedSize(true);
        pokemon_list_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(Objects.requireNonNull(getActivity()), R.dimen.spacing);
        pokemon_list_recyclerview.addItemDecoration(itemOffsetDecoration);

        fetchData();

        return view;
    }
    private void fetchData() {
        compositeDisposable.add(iPokemonDex.getListPokemon()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Pokedex>(){
                @Override
                public void accept(Pokedex pokedex) throws Exception{
                    Common.commonPokemonList = pokedex.getPokemon();
                    PokemonListAdapter adapter = new PokemonListAdapter(getActivity(), Common.commonPokemonList);

                    pokemon_list_recyclerview.setAdapter(adapter);
                }
            })
        );
    }
}
