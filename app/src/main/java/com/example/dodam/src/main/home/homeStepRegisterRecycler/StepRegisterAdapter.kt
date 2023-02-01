package com.example.dodam.src.main.home.homeStepRegisterRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dodam.R
import com.example.dodam.databinding.FragmentStepRegisterBinding
import com.example.dodam.databinding.HomeStepItemBinding
import com.example.dodam.databinding.StepRegisterItemBinding
import com.example.dodam.src.main.home.homeStepRecycler.HomeStepAdapter

class StepRegisterAdapter (val data: List<StepRegisterItem>): RecyclerView.Adapter<StepRegisterAdapter.ViewHolder>() {
    lateinit var binding: StepRegisterItemBinding

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepRegisterAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.step_register_item, parent, false)
        return StepRegisterAdapter.ViewHolder(StepRegisterItemBinding.bind(inflatedView))
    }

    override fun onBindViewHolder(holder: StepRegisterAdapter.ViewHolder, position: Int) {
        val item = data[position]
        val listener = View.OnClickListener {it ->
            Toast.makeText(it.context, "Clicked: ${item.stepName}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
//            bind(listener, item)
            binding.stepName.text = item.stepName
            binding.stepPeriod.text = item.stepPeriod
        }
    }

    class ViewHolder(val binding: StepRegisterItemBinding) : RecyclerView.ViewHolder(binding.root) {


//        fun bind(listener: View.OnClickListener, item: HomeStepItem) {
//            binding.stepName.text = item.stepName
//        }
    }

}

//class StepRegisterAdapter(
//    private val context: Context,
//    val contentId: Int,
//    val data: List<StepRegisterViewModel>): RecyclerView.Adapter<StepRegisterViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepRegisterViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(contentId, parent, false)
//        return StepRegisterViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    interface ItemClickListener {
//        fun onClick(view: View, position: Int)
//    }
//
//    private lateinit var itemClickListener: ItemClickListener
//
//    fun setItemClickListener(itemClickListener: ItemClickListener) {
//        this.itemClickListener = itemClickListener
//    }
//
//    override fun onBindViewHolder(holder: StepRegisterViewHolder, position: Int) {
//
//    }
//}