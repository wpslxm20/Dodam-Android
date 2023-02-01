package com.example.dodam.src.main.home.homeStepRecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dodam.R
import com.example.dodam.databinding.HomeStepItemBinding

class HomeStepAdapter(val stepList: List<HomeStepItem>): RecyclerView.Adapter<HomeStepAdapter.ViewHolder>(){

    lateinit var binding: HomeStepItemBinding

    override fun getItemCount() = stepList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeStepAdapter.ViewHolder {

        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_step_item, parent, false)
        return HomeStepAdapter.ViewHolder(HomeStepItemBinding.bind(inflatedView))

    }

    override fun onBindViewHolder(holder: HomeStepAdapter.ViewHolder, position: Int) {
        val item = stepList[position]
        val listener = View.OnClickListener {it ->
            Toast.makeText(it.context, "Clicked: ${item.stepName}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
//            bind(listener, item)
            binding.stepName.text = item.stepName
            if (position==0){
                binding.stepImage.setImageResource(R.drawable.home_first_step_image)
            }
            else if (position == stepList.size - 1){
                binding.stepImage.setImageResource(R.drawable.home_final_step_image)
            }
            else{
                binding.stepImage.setImageResource(R.drawable.home_middle_step_image)
            }
        }
    }

    class ViewHolder(val binding: HomeStepItemBinding) : RecyclerView.ViewHolder(binding.root) {


//        fun bind(listener: View.OnClickListener, item: HomeStepItem) {
//            binding.stepName.text = item.stepName
//        }
    }


}