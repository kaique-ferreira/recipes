package com.aruana.recipes.home

import com.aruana.recipes.domain.CachedSingleUseCase
import com.aruana.recipes.repository.IngredientModel
import io.reactivex.disposables.Disposable

abstract class FindAllIngredients : CachedSingleUseCase<List<IngredientModel>, FindAllIngredients.Params> {

    override var disposable: Disposable? = null

    class Params
}