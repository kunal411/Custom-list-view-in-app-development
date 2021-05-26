package com.example.custom_list_view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    val expense = arrayOf(Exp("Groceries", "$5000.00"),
        Exp("Transportation", "$8000.00"),
        Exp("Rent", "$50000"),
        Exp("Cell Phone", "$800"),
        Exp("Utility Bills", "$6500"),
        Exp("Insurance", "$5000") ,
        Exp("Groceries", "$5000.00"),
        Exp("Rent", "$50000"),
        Exp("Cell Phone", "$800"),
        Exp("Utility Bills", "$6500"),
        Exp("Insurance", "$5000") ,
        Exp("Groceries", "$5000.00"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFavourites()

        val list_view = findViewById<ListView>(R.id.Grocery_list)
        val expense_adapter = ExpenseAdapter(expense)

        list_view.adapter = expense_adapter

        list_view.setOnItemClickListener{parent, view, position, id ->

            val expen : Exp = expense_adapter.getItem(position)
            expen?.let {
                expen.fav = !expen.fav
                expense_adapter.notifyDataSetChanged()
            }

            saveFavourites()
        }
    }

    private fun saveFavourites(){
        val favourites = expense.filter { it.fav }.map { it.exp_type }

        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with((sharedPref.edit())){
            putStringSet("ABC", favourites.toSet())
            commit()
        }
    }

    private fun loadFavourites(){
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val favourites = sharedPref.getStringSet("ABC", null)

        favourites?.forEach{expens ->
            val exp = expense.find{it.exp_type == expens}
            exp?.fav = true
        }
    }
}