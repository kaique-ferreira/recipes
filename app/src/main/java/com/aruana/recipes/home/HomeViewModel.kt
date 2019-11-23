package com.aruana.recipes.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aruana.recipes.repository.IngredientModel
import com.aruana.recipes.repository.RecipeModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val findRecipeByIngredient: FindRecipeByIngredient,
                                        private val findAllIngredients: FindAllIngredients,
                                        val adapter: RecipesAdapter) : ViewModel() {

    private val _recipes = MutableLiveData<List<RecipeModel>>()

    val recipes: LiveData<List<RecipeModel>> get() = _recipes

    private val _ingredients = MutableLiveData<List<IngredientModel>>()

    val ingredients: LiveData<List<IngredientModel>> get() = _ingredients

    override fun onCleared() {
        findRecipeByIngredient.dispose()
        findAllIngredients.dispose()
    }

    fun findRecipeByIngredient(ingredient: String) {
        if (ingredient.isEmpty() || ingredient.length < QUERY_STRING_THRESHOLD) {
            _recipes.value = emptyList()
            return
        }

        findRecipeByIngredient.invoke(
                onSuccess = { _recipes.value = it },
                onError = { Log.e(TAG, it.message ?: "") },
                params = FindRecipeByIngredient.Params(ingredient))
    }

    fun findAllIngredients() {
        findAllIngredients.invoke(
                onSuccess = { _ingredients.value = it },
                onError = { Log.e(TAG, it.message ?: "") },
                params = FindAllIngredients.Params())
    }

    companion object {
        private const val QUERY_STRING_THRESHOLD = 3
        private const val TAG = "HomeViewModel"
    }
}