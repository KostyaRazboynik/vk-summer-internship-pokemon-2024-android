package com.kostyarazboynik.pokemon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kostyarazboynik.feature_pokemon_list.ui.PokemonListFragment
import com.kostyarazboynik.pokemon.dagger.AppComponent
import com.kostyarazboynik.pokemon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    init {
        AppComponent.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val fragment = PokemonListFragment.newInstance(binding.frameContent.id)
        createFragment(binding, fragment)
    }

    private fun createFragment(binding: ActivityMainBinding, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(binding.frameContent.id, fragment)
        transaction.commit()
    }
}