package com.example.myrefrigerator

import android.annotation.SuppressLint
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myrefrigerator.databinding.ActivityMainBinding
import com.example.myrefrigerator.databinding.ActivityRecipeDetailBinding
import com.example.myrefrigerator.databinding.ActivityRecipeMainBinding
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

class RecipeDetailActivity : AppCompatActivity() {

    class MyFragmentPagerAdapter(activity: FragmentActivity, private val fragments: List<Fragment>, private val data: Bundle?): FragmentStateAdapter(activity){

        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment {
            val fragment = fragments[position]
            fragment.arguments = data
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.rdtoolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val bundleData = intent.getBundleExtra("bundleData")

        val fragments = listOf(IntroFragment(), OrderFragment(), ReviewFragment())

        val adapter = MyFragmentPagerAdapter(this, fragments, bundleData)
        binding.viewpager.adapter = adapter

        val tabTitles = listOf("요리 준비", "레시피", "리뷰")
        TabLayoutMediator(binding.tabs, binding.viewpager){
                tab, position -> tab.text = tabTitles[position]
        }.attach()

        val namedata = bundleData?.getString("rcpname")
        val mingredata = bundleData?.getString("rcpmingre")
        val imagedata = bundleData?.getString("rcpimage")
        val ingredata = bundleData?.getString("rcpingre")
        val manualdata = bundleData?.getStringArrayList("rcpmanual")


        supportActionBar?.title = namedata

        // CollapsingToolbarLayout에 동적인 타이틀 설정
        val collapsingToolbarLayout: CollapsingToolbarLayout = findViewById(R.id.toolbarRecipe)
        collapsingToolbarLayout.title = namedata

        val image :ImageView = findViewById(R.id.rdimage)

        Picasso.get().load(imagedata).into(image)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        return super.onCreateOptionsMenu(menu)
    }
}




