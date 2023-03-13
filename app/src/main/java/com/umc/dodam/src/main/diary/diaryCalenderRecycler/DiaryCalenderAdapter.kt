package com.umc.dodam.src.main.diary.diaryCalenderRecycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.dodam.R
import com.umc.dodam.databinding.DiaryCalenderItemBinding

class DiaryCalenderAdapter (val dayList: ArrayList<String>) : RecyclerView.Adapter<DiaryCalenderAdapter.ViewHolder>() {

    lateinit var binding: DiaryCalenderItemBinding
    private var selectCheck: ArrayList<Boolean> = arrayListOf()

    //    var dayList = mutableListOf<String>()
    class ViewHolder(val binding: DiaryCalenderItemBinding): RecyclerView.ViewHolder(binding.root){
        fun viewClick(position: Int, selectCheck: MutableList<Boolean>){
            itemView.setOnClickListener{
                binding.viewTvDay.setBackgroundResource(R.drawable.diary_select_day_background_image)
//                for ( i: Int in (0..41)){
//                    if(i==position){
//                        selectCheck[i] = true
//                    }
//                    else {
////                        binding.viewTvDay.setBackgroundResource(R.color.transparency)
//                        selectCheck[i] = false
//                    }
//                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DiaryCalenderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        binding.tvDay.setText(dayList.get(position))



        //날짜를 클릭했을 때 일기가 있으면 다이어리 디테일 페이지로, 없으면 일기 쓰기 페이지
        //api 연동 후 수정(없다는 가정 하)
//        binding.viewDay.setOnClickListener(View.OnClickListener {
//
//        })
        holder.viewClick(position, selectCheck)
        notifyDataSetChanged()
    }

    override fun getItemCount() = dayList.size
}