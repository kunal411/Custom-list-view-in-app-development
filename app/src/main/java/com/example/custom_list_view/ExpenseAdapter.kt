package com.example.custom_list_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class ExpenseAdapter(val expense_data : Array<Exp>) : BaseAdapter(){

    override fun getCount(): Int {
        return expense_data.size
    }

    override fun getItem(position: Int): Exp {
        return expense_data[position]
    }

    override fun getItemId(position: Int): Long {
        return expense_data[position].exp_type.hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val convertView = LayoutInflater.from(parent!!.context).inflate(R.layout.new_layout, parent, false)

        val expense_name : TextView = convertView.findViewById(R.id.ExpenseName)
        val expense_price : TextView = convertView.findViewById(R.id.ExpensePrice)

        expense_name.text = expense_data[position].exp_type
        expense_price.text = expense_data[position].cost

        if(expense_data[position].fav){
            convertView.setBackgroundColor(ContextCompat.getColor(convertView.context, R.color.teal_200))
        }else{
            convertView.setBackgroundColor(ContextCompat.getColor(convertView.context, R.color.white))
        }
        return convertView
    }
}