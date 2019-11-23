package com.aruana.recipes.home

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.aruana.recipes.R
import com.aruana.recipes.databinding.DataBindingAdapter
import com.aruana.recipes.databinding.DataBindingViewHolder
import com.aruana.recipes.repository.RecipeModel
import javax.inject.Inject

class RecipesAdapter @Inject constructor() : DataBindingAdapter<RecipeModel>(DiffCallback()) {

    var itemClickListener: ((Int, View) -> Unit)? = null

    class DiffCallback : DiffUtil.ItemCallback<RecipeModel>() {
        override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel) = oldItem.idMeal == newItem.idMeal

        override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel) = oldItem.idMeal == newItem.idMeal
    }

    override fun getItemViewType(position: Int) = R.layout.recipe_item

    override fun onBindViewHolder(holder: DataBindingViewHolder<RecipeModel>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(position, it)
        }
    }
}