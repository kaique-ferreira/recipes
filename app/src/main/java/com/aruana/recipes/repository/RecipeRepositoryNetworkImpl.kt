package com.aruana.recipes.repository

import com.aruana.recipes.networking.RecipesService
import io.reactivex.Single
import javax.inject.Inject

class RecipeRepositoryNetworkImpl @Inject constructor(private val recipesService: RecipesService) : RecipeRepository {

    override fun findIngredients(): Single<List<IngredientModel>> = recipesService.allIngredients().map { it.meals }

    override fun findRecipeByIngredient(ingredient: String): Single<List<RecipeModel>> = recipesService
            .recipeFilteredByIngredient(ingredient)
            .map { apiModel ->
                apiModel.meals ?: emptyList()
            }

    override fun findRecipeById(id: Int): Single<List<RecipeModel>> = recipesService
            .recipeById(id)
            .map { apiModel ->
                apiModel.meals ?: emptyList()
            }
}