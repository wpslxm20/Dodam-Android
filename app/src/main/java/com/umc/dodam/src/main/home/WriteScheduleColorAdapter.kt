package com.umc.dodam.src.main.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.umc.dodam.R
import com.umc.dodam.databinding.StepRegisterItemBinding
import com.umc.dodam.databinding.WriteScheduleColorItemBinding

class WriteScheduleColorAdapter(private val context: Context, val data:List<Int>) : RecyclerView.Adapter<WriteScheduleColorAdapter.ViewHolder>(){
    lateinit var binding: WriteScheduleColorItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.write_schedule_color_item,parent,false)
        return WriteScheduleColorAdapter.ViewHolder(context, WriteScheduleColorItemBinding.bind(view))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(val context: Context, val binding: WriteScheduleColorItemBinding) : RecyclerView.ViewHolder(binding.root) {



        fun bind(item: Int) {
            binding.layoutWriteScheduleColorItem.setBackgroundColor(ContextCompat.getColor(context, item));
        }
    }
}