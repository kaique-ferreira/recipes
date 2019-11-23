package com.aruana.recipes.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aruana.recipes.MyApplication
import com.aruana.recipes.detail.DetailViewModel
import com.aruana.recipes.home.HomeViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, RecipeModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    val homeViewModel: HomeViewModel

    val detailViewModel: DetailViewModel
}

inline fun <reified T : ViewModel> AppCompatActivity.createViewModel(crossinline factory: () -> T): T = T::class.java.let { clazz ->
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass == clazz) {
                @Suppress("UNCHECKED_CAST")
                return factory() as T
            }
            throw IllegalArgumentException("Unexpected argument: $modelClass")
        }
    }).get(clazz)
}

fun Fragment.createHomeViewModel() =
        (requireActivity() as AppCompatActivity).createViewModel {
            (requireActivity().application as MyApplication).appComponent.homeViewModel
        }

fun Fragment.createDetailViewModel() =
        (requireActivity() as AppCompatActivity).createViewModel {
            (requireActivity().application as MyApplication).appComponent.detailViewModel
        }