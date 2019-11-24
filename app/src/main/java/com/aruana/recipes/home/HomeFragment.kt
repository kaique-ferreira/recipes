package com.aruana.recipes.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.aruana.recipes.R
import com.aruana.recipes.databinding.FragmentHomeBinding
import com.aruana.recipes.detail.DetailFragment.Companion.DETAIL_TRANSITION_NAME
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

        setUpClickListeners()

        setUpViewModelObservers(view)

        viewModel.initializeAutoCompleteList()
    }

    private fun setUpClickListeners() {
        viewModel.adapter.itemClickListener = { index, itemView ->
            tryToNavigateToRecipeDetail(index, itemView)
        }

        editTextIngredientQuery.setOnClickListener {
            (it as AutoCompleteTextView).text.clear()
        }
    }

    private fun setUpViewModelObservers(view: View) {
        viewModel.recipes.observe(this, Observer {
            if (it.isNotEmpty()) {
                closeSoftKeyboard(view)
            }
            viewModel.adapter.submitList(it)
        })

        viewModel.ingredients.observe(this, Observer { ingredientList ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.select_dialog_item, ingredientList.map { it.strIngredient }.toTypedArray())
            editTextIngredientQuery.setAdapter(adapter)
        })
    }

    private fun closeSoftKeyboard(view: View) {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun tryToNavigateToRecipeDetail(index: Int, itemView: View) {
        viewModel.recipes.value?.get(index)?.let { selectedRecipe ->
            itemView.recipeImage.transitionName = DETAIL_TRANSITION_NAME

            val extras = FragmentNavigatorExtras(itemView.recipeImage to DETAIL_TRANSITION_NAME)

            val action = HomeFragmentDirections.actionHomeDestToDetail(selectedRecipe.idMeal)
            findNavController().navigate(action, extras)
        }
    }
}