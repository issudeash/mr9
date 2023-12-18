package com.example.myrefrigerator

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrefrigerator.databinding.ActivityRecipeMainBinding
import com.example.myrefrigerator.databinding.ItemRecyclerviewBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent

class MyViewHolder (val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter (val names:MutableList<String>, val mingres:MutableList<String>, val images:MutableList<String>, val ingres:MutableList<String>, val manuallists:MutableList<List<String>>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = names.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding

        binding.recipeName.text = names[position]
        binding.recipeMainingredient.text = mingres[position]

        Picasso.get().load(images[position]).into(binding.recipeImage)

        binding.root.setOnClickListener {
            // 전달할 데이터를 번들에 담기
            val bundleData = Bundle().apply {
                putString("rcpname", names[position])
                putString("rcpmingre", mingres[position])
                putString("rcpimage", images[position])
                putString("rcpingre", ingres[position])
                putStringArrayList("rcpmanual", ArrayList(manuallists[position]))
            }

            // RecipeDetailActivity로 이동
            val intent = Intent(it.context, RecipeDetailActivity::class.java)
            intent.putExtra("bundleData", bundleData)

            it.context.startActivity(intent)
        }

    }
}


