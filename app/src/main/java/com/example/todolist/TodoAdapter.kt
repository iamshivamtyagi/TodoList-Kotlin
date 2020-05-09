package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(val list: List<TodoModel>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todoModel: TodoModel) {
            with(itemView) {
                val colors = resources.getIntArray(R.array.random_colors)
                val randomColor = colors[Random().nextInt(colors.size)]

                viewCardTag.setBackgroundColor(randomColor)

                txtShowTime.text = todoModel.title

                txtShowTask.text = todoModel.description

                txtShowCategory.text = todoModel.category

                updateTime(todoModel.time)

                updateDate(todoModel.date)
            }
        }

        private fun updateTime(time: Long) {
            // 04:20 AM
            val myFormat = "h:mm a"

            val sdf = SimpleDateFormat(myFormat)
            itemView.txtShowTime.text = sdf.format(Date(time))
        }

        private fun updateDate(date: Long) {
            // FRI, 5 May 2020
            val myFormat = "EEE, d MMM yyyy"

            val sdf = SimpleDateFormat(myFormat)

            itemView.txtShowDate.text = sdf.format(Date(date))
        }
    }
}