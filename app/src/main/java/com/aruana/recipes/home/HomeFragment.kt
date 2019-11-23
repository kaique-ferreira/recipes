package com.aruana.recipes.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.aruana.recipes.R
import com.aruana.recipes.databinding.FragmentHomeBinding
import com.aruana.recipes.di.createHomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recipe_item.view.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = createHomeViewModel()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.adapter.itemClickListener = { index, itemView ->
            viewModel.recipes.value?.get(index)?.let { selectedRecipe ->
                itemView.recipeImage.transitionName = selectedRecipe.idMeal.toString()
                val action = HomeFragmentDirections.actionHomeDestToDetail(selectedRecipe.idMeal)
                findNavController().navigate(action)
            }
        }

        viewModel.recipes.observe(this, Observer {
            viewModel.adapter.submitList(it)
        })

        viewModel.ingredients.observe(this, Observer { ingredientList ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.select_dialog_item, ingredientList.map { it.strIngredient }.toTypedArray())
            editTextIngredientQuery.setAdapter(adapter)
        })

        viewModel.findAllIngredients()
    }
}