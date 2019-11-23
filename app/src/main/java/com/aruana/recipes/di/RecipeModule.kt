package com.aruana.recipes.di

import com.aruana.recipes.detail.FindRecipeById
import com.aruana.recipes.detail.FindRecipeByIdImpl
import com.aruana.recipes.home.FindAllIngredients
import com.aruana.recipes.home.FindAllIngredientsImpl
import com.aruana.recipes.home.FindRecipeByIngredient
import com.aruana.recipes.home.FindRecipeByIngredientImpl
import com.aruana.recipes.repository.RecipeRepository
import com.aruana.recipes.repository.RecipeRepositoryNetworkImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RecipeModule {

    @Binds
    abstract fun provideRecipeByIngredientUseCase(useCase: FindRecipeByIngredientImpl): FindRecipeByIngredient

    @Binds
    abstract fun provideRecipeByIdUseCase(useCase: FindRecipeByIdImpl): FindRecipeById

    @Binds
    abstract fun provideAllIngredientsUseCase(useCase: FindAllIngredientsImpl): FindAllIngredients

    @Binds
    abstract fun provideRepository(repository: RecipeRepositoryNetworkImpl): RecipeRepository
}