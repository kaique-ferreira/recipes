package com.aruana.recipes.home

import com.aruana.recipes.repository.RecipeModel
import com.aruana.recipes.repository.RecipeRepository
import io.reactivex.Single
import javax.inject.Inject

class FindRecipeByIngredientImpl @Inject constructor(private val repository: RecipeRepository) : FindRecipeByIngredient() {

    override fun createSingleUseCase(params: Params): Single<List<RecipeModel>> = repository.findRecipeByIngredient(params.ingredient)
}