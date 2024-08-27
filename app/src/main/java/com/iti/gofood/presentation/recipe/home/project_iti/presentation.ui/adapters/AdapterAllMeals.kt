package com.example.project_iti.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_iti.data.Remote.Retrofit.Meal
import com.iti.gofood.databinding.ItemMealBinding

class AdapterAllMeals(private var mealList: List<Meal>) : RecyclerView.Adapter<AdapterAllMeals.ViewHolder>() {

    private var onItemClick: ((Meal) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMealBinding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mealList[position])
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    inner class ViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Meal) {
            binding.name.text = recipe.strMeal
            Glide.with(binding.pic.context)
                .load(recipe.strMealThumb)
                .into(binding.pic)
            binding.root.setOnClickListener {
                onItemClick?.invoke(recipe)
            }
        }
    }

    fun setOnItemClickListener(listener: (Meal) -> Unit) {
        onItemClick = listener
    }

    fun update(newList: List<Meal>) {
        mealList = newList
        notifyDataSetChanged()
    }
}
