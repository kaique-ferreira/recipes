package com.aruana.recipes.detail

import com.aruana.recipes.domain.SingleUseCase
import com.aruana.recipes.repository.RecipeModel
import io.reactivex.disposables.Disposable

abstract class FindRecipeById : SingleUseCase<RecipeModel, FindRecipeById.Params> {

    override var disposable: Disposable? = null

    data class Params(val id: Int)
}