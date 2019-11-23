package com.aruana.recipes.home

import com.aruana.recipes.domain.SingleUseCase
import com.aruana.recipes.repository.RecipeModel
import io.reactivex.disposables.Disposable

abstract class FindRecipeByIngredient : SingleUseCase<List<RecipeModel>, FindRecipeByIngredient.Params> {

    override var disposable: Disposable? = null

    data class Params(val ingredient: String)
}