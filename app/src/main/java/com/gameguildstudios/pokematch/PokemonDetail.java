package com.gameguildstudios.pokematch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gameguildstudios.pokematch.Adapter.PokemonEvolutionAdapter;
import com.gameguildstudios.pokematch.Adapter.PokemonTypeAdapter;
import com.gameguildstudios.pokematch.Common.Common;
import com.gameguildstudios.pokematch.Model.Pokemon;

import org.w3c.dom.Text;

import java.util.ConcurrentModificationException;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokemonDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonDetail extends Fragment {

    /////////

    ImageView pokemon_img;
    TextView pokemon_name, pokemon_height, pokemon_weight;
    RecyclerView recycler_type, recycler_weakness, recycler_next_evolution, recycler_prev_evolution;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PokemonDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PokemonDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static PokemonDetail newInstance(String param1, String param2) {
        PokemonDetail fragment = new PokemonDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView =  inflater.inflate(R.layout.fragment_pokemon_detail, container, false);

        Pokemon pokemon;
        //get position from argument
        if (getArguments().get("num") == null)
            pokemon = Common.commonPokemonList.get(getArguments().getInt("position"));
        else
            pokemon = Common.findPokemonByNum(getArguments().getString("num"));

        pokemon_img = (ImageView) itemView.findViewById(R.id.pokemon_image);
        pokemon_name = (TextView) itemView.findViewById(R.id.name);
        pokemon_weight = (TextView) itemView.findViewById(R.id.weight);
        pokemon_height = (TextView) itemView.findViewById(R.id.height);

        recycler_type = (RecyclerView)itemView.findViewById(R.id.recycler_type);
        recycler_type.setHasFixedSize(true);
        recycler_type.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        recycler_weakness = (RecyclerView)itemView.findViewById(R.id.recycler_weakness);
        recycler_weakness.setHasFixedSize(true);
        recycler_weakness.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        recycler_prev_evolution = (RecyclerView)itemView.findViewById(R.id.recycler_prev_evolution);
        recycler_prev_evolution.setHasFixedSize(true);
        recycler_prev_evolution.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        recycler_next_evolution = (RecyclerView)itemView.findViewById(R.id.recycler_next_evolution);
        recycler_next_evolution.setHasFixedSize(true);
        recycler_next_evolution.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        setDetailPokemon(pokemon);

        return itemView;
    }

    private void setDetailPokemon(Pokemon pokemon) {
        //load image
        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemon_img);

        //set info
        pokemon_name.setText(pokemon.getName());

        pokemon_weight.setText("Weight: "+ pokemon.getWeight());
        pokemon_height.setText("Height: " + pokemon.getHeight());

        //set type
        PokemonTypeAdapter typeAdapter = new PokemonTypeAdapter(getActivity(), pokemon.getType());
        recycler_type.setAdapter(typeAdapter);

        //set weakness
        PokemonTypeAdapter weaknessAdapter = new PokemonTypeAdapter(getActivity(), pokemon.getWeaknesses());
        recycler_weakness.setAdapter(weaknessAdapter);

        //set evolution
        PokemonEvolutionAdapter prevEvolutionAdapter = new PokemonEvolutionAdapter(getActivity(), pokemon.getPrev_evolution());
        recycler_prev_evolution.setAdapter(prevEvolutionAdapter);
        PokemonEvolutionAdapter nextEvolutionAdapter = new PokemonEvolutionAdapter(getActivity(), pokemon.getNext_evolution());
        recycler_next_evolution.setAdapter(nextEvolutionAdapter);

    }

    static PokemonDetail instance;

    public static PokemonDetail getInstance() {
        if (instance==null){
            instance = new PokemonDetail();
        }
        return instance;
    }
}
