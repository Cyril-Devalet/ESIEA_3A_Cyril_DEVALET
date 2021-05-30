package com.example.esiea_3a_cyril_devalet.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea_3a_cyril_devalet.R
import com.example.esiea_3a_cyril_devalet.presentation.api.PokeAPI
import com.example.esiea_3a_cyril_devalet.presentation.api.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PokemonListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val adapter = PokemonAdapter(listOf())

    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemon_recyclerview)

        recyclerView.apply {
            layoutManager = this@PokemonListFragment.layoutManager
            adapter = this@PokemonListFragment.adapter
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokeAPI: PokeAPI = retrofit.create(PokeAPI::class.java)

        pokeAPI.getPokemonList().enqueue(object: Callback<PokemonResponse>{
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                //TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<PokemonResponse>,  response: Response<PokemonResponse>) {
                if(response.isSuccessful && response.body() != null){
                    val pokemonResponse = response.body()!!
                    adapter.updateList(pokemonResponse.results)
                }
            }
        })


    }
}