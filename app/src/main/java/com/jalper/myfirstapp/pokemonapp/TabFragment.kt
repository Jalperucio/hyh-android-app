package com.jalper.myfirstapp.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.FragmentTabBinding
import java.lang.IllegalArgumentException

class TabFragment : Fragment() {

    private lateinit var binding: FragmentTabBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTabBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {

        val tabLayout = binding.tlPokemonTabs
        val viewPager = binding.vp2PokemonViewpager

        viewPager.adapter = PokemonViewPagerAdapter(this)


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.pokemon_bulbasaur)
                1 -> getString(R.string.pokemon_squirtle)
                else -> getString(R.string.pokemon_charmander)
            }
        }.attach()
    }

    private inner class PokemonViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount() = 3
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> BulbasaurFragment()
                1 -> SquirtleFragment()
                2 -> CharmanderFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }
}

