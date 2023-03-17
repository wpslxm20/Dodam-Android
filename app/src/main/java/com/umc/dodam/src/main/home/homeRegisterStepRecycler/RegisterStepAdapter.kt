package com.umc.dodam.src.main.home.homeRegisterStepRecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.umc.dodam.R
import com.umc.dodam.databinding.StepRegisterItemBinding
import com.umc.dodam.src.main.home.ItemTouchHelperListener
import com.umc.dodam.src.main.home.RegisterStepFragment
import java.util.*
import kotlin.collections.ArrayList

class RegisterStepAdapter (
    val data: MutableList<RegisterStepItem>,
    val stepClick: (id: Int) -> Unit
    ): RecyclerView.Adapter<RegisterStepAdapter.ViewHolder>(),
    ItemTouchHelperListener {

    lateinit var binding: StepRegisterItemBinding

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterStepAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.step_register_item, parent, false)
        return RegisterStepAdapter.ViewHolder(StepRegisterItemBinding.bind(inflatedView))
    }

    override fun onBindViewHolder(holder: RegisterStepAdapter.ViewHolder, position: Int) {
        val item = data[position]
        val listener = View.OnClickListener {it ->
            Toast.makeText(it.context, "Clicked: ${item.stepName}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
//            bind(listener, item)
            binding.stepName.text = item.stepName
            binding.stepPeriodStart.text = item.stepStartPeriod
            binding.stepPeriodEnd.text = item.stepEndPeriod
        }

        // recyclerview item click
        holder.itemView.setOnClickListener {
            stepClick.invoke(data[position].stepId)
        }
    }

    class ViewHolder(val binding: StepRegisterItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    // drag & drop 구현
    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(data, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }
}