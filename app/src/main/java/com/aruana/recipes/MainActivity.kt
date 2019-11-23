package com.aruana.recipes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(navController = findNavController(R.id.navigationHostFragment))
    }

    override fun onSupportNavigateUp() =
            findNavController(R.id.navigationHostFragment).navigateUp()
}
