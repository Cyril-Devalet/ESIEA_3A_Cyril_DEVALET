package com.example.esiea_3a_cyril_devalet.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea_3a_cyril_devalet.R

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

        val pokeList = arrayListOf<Pokemon>().apply{
            add(Pokemon( "Pikachu"))
            add(Pokemon( "Raichu"))
            add(Pokemon( "Mew"))
            add(Pokemon( "Rondoudou"))
        }

        adapter.updateList(pokeList)

    }
}