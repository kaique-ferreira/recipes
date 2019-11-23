package com.aruana.recipes.home

import com.aruana.recipes.repository.IngredientModel
import com.aruana.recipes.repository.RecipeRepository
import io.reactivex.Single
import javax.inject.Inject

class FindAllIngredientsImpl @Inject constructor(val repository: RecipeRepository) : FindAllIngredients() {

    private var cachedIngredients: List<IngredientModel> = emptyList()

    override fun addToCache(model: List<IngredientModel>) {
        cachedIngredients = model
    }

    override fun shouldCacheResult() = true

    override fun createSingleUseCase(params: Params): Single<List<IngredientModel>> {
        if (cachedIngredients.isNotEmpty()) {
            return Single.just(cachedIngredients)
        }

        return repository.findIngredients()
    }
}