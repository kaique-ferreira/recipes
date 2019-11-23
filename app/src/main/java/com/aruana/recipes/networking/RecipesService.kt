package com.aruana.recipes.networking

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesService {

    @GET("filter.php")
    fun recipeFilteredByIngredient(@Query("i") ingredient: String): Single<ApiRecipeModel>

    @GET("lookup.php")
    fun recipeById(@Query("i") id: Int): Single<ApiRecipeModel>

    @GET("list.php?i=list")
    fun allIngredients(): Single<ApiIngredientModel>

    companion object {
        const val API_ENDPOINT = "https://www.themealdb.com/api/json/v1/1/"
    }
}