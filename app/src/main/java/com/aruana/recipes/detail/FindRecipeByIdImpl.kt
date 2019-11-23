package com.aruana.recipes.detail

import com.aruana.recipes.repository.RecipeModel
import com.aruana.recipes.repository.RecipeRepository
import javax.inject.Inject

class FindRecipeByIdImpl @Inject constructor(private val repository: RecipeRepository) : FindRecipeById() {

    override fun addToCache(model: RecipeModel) {
    }

    override fun createSingleUseCase(params: Params) = repository.findRecipeById(params.id).map { formatApiResult(it) }

    private fun formatApiResult(apiResult: List<RecipeModel>): RecipeModel {
        val recipe = apiResult.first()
        with(recipe) {
            ingredientListFormatted =
                    createIngredientLine(strMeasure1, strIngredient1) +
                            createIngredientLine(strMeasure2, strIngredient2) +
                            createIngredientLine(strMeasure3, strIngredient3) +
                            createIngredientLine(strMeasure4, strIngredient4) +
                            createIngredientLine(strMeasure5, strIngredient5) +
                            createIngredientLine(strMeasure6, strIngredient6) +
                            createIngredientLine(strMeasure7, strIngredient7) +
                            createIngredientLine(strMeasure8, strIngredient8) +
                            createIngredientLine(strMeasure9, strIngredient9) +
                            createIngredientLine(strMeasure10, strIngredient10) +
                            createIngredientLine(strMeasure11, strIngredient11) +
                            createIngredientLine(strMeasure12, strIngredient12) +
                            createIngredientLine(strMeasure13, strIngredient13) +
                            createIngredientLine(strMeasure14, strIngredient14) +
                            createIngredientLine(strMeasure15, strIngredient15) +
                            createIngredientLine(strMeasure16, strIngredient16) +
                            createIngredientLine(strMeasure17, strIngredient17) +
                            createIngredientLine(strMeasure18, strIngredient18) +
                            createIngredientLine(strMeasure19, strIngredient19) +
                            createIngredientLine(strMeasure20, strIngredient20)
        }

        return recipe
    }

    private fun createIngredientLine(measure: String, ingredient: String) =
            if (ingredient.isNotEmpty()) {
                "\u2022 $measure $ingredient\n"
            } else {
                ""
            }
}