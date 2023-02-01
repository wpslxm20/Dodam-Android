package com.example.dodam.src.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StepRegisterAdapter(
    private val context: Context,
    val contentId: Int,
    val data: List<StepRegisterViewModel>): RecyclerView.Adapter<StepRegisterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepRegisterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(contentId, parent, false)
        return StepRegisterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onBindViewHolder(holder: StepRegisterViewHolder, position: Int) {

    }
}