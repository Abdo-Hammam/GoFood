package com.example.project_iti.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_iti.data.Remote.Retrofit.Meal
import com.iti.gofood.databinding.ItemMealBinding

class AdapterAllMeals(private var mealList: List<Meal>) : RecyclerView.Adapter<AdapterAllMeals.ViewHolder>() {

    private lateinit var onClick: (String) -> Unit

    fun setOnClick(onClick: (String) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMealBinding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealList[position]
        holder.binding.name.text = meal.strMeal
        Glide.with(holder.itemView.context).load(meal.strMealThumb).into(holder.binding.pic)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    inner class ViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val id = mealList[layoutPosition].idMeal
                onClick.invoke(id)
            }
        }
    }

    fun update(newList: List<Meal>) {
        mealList = newList
        notifyDataSetChanged()
    }
}
