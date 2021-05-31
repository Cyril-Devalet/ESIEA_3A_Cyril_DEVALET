package com.example.esiea_3a_cyril_devalet.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.esiea_3a_cyril_devalet.R
import com.example.esiea_3a_cyril_devalet.presentation.Singletons
import com.example.esiea_3a_cyril_devalet.presentation.api.PokemonDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailFragment : Fragment() {

    private lateinit var textViewName: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.pokemon_detail_name)
        callAPI()
    }

    private fun callAPI() {
        Singletons.pokeAPI.getPokemonDetail("1").enqueue(object : Callback<PokemonDetailResponse>{
            override fun onFailure(
                    call: Call<PokemonDetailResponse>,
                    t: Throwable
            ) {

            }

            override fun onResponse(
                    call: Call<PokemonDetailResponse>,
                    response: Response<PokemonDetailResponse>
            ) {
                if(response.isSuccessful && response.body() != null){
                    textViewName.text = response.body()!!.name
                }

            }
        })
    }
}