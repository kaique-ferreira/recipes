package com.aruana.recipes.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aruana.recipes.repository.RecipeModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val findRecipeById: FindRecipeById) : ViewModel() {

    private val _recipe = MutableLiveData<RecipeModel>()

    val recipe: LiveData<RecipeModel> get() = _recipe

    override fun onCleared() = findRecipeById.dispose()

    fun getRecipeData(recipeId: Int) = findRecipeById.invoke(
            onSuccess = { _recipe.value = it },
            onError = { Log.e(TAG, it.message ?: "") },
            params = FindRecipeById.Params(recipeId))

    companion object {
        private const val TAG = "DetailViewModel"
    }
}