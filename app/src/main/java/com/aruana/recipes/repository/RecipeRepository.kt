package com.aruana.recipes.repository

import io.reactivex.Single

interface RecipeRepository {

    fun findRecipeByIngredient(ingredient: String): Single<List<RecipeModel>>

    fun findRecipeById(id: Int): Single<List<RecipeModel>>

    fun findIngredients(): Single<List<IngredientModel>>
}