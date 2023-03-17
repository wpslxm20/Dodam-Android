package com.umc.dodam.src.main.myPage.inquiryRecycler

import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.util.remove
import androidx.recyclerview.widget.RecyclerView
import com.umc.dodam.R
import com.umc.dodam.databinding.InquiryItemBinding
import kotlinx.android.synthetic.main.inquiry_item.view.*

class InquiryAdapter(
    val data: MutableList<InquiryItem>,
    val inquiryClick: (id: Int) -> Unit
    ): RecyclerView.Adapter<InquiryAdapter.ViewHolder>() {

    lateinit var binding: InquiryItemBinding
    // item을 클릭 했는지 확인하는 배열
    private var selectedItems = SparseBooleanArray()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InquiryAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.inquiry_item, parent, false)
        binding = InquiryItemBinding.bind(inflatedView)

        // 처음에 숨길 부분: 문의 내용, 문의 답변, 수정/삭제 버튼
        binding.textInquiryContent.visibility = View.GONE
        binding.textInquiryAnswer.visibility = View.GONE
        binding.groupBtn.visibility = View.GONE

        return InquiryAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InquiryAdapter.ViewHolder, position: Int) {
        val item = data[position]
        // recyclerview의 item과 data match
        holder.apply {
            binding.textInquiryTitle.text = item.inquiryTitle
            binding.textInquiryContent.text = item.inquiryContent
            binding.textInquiryAnswer.text = item.inquiryAnswer
        }
        // recyclerview click
        holder.itemView.setOnClickListener {
            if(selectedItems.get(position, false)) {
                // item 클릭 시
                selectItem(holder, position)
            } else {
                // item 재클릭 시(해제)
                clearItem(holder, position)
            }
        }
        // 문의 수정
        holder.itemView.btn_inquiry_modify.setOnClickListener {

        }
        // 문의 삭제
        holder.itemView.btn_inquiry_delete.setOnClickListener {
            removeItem(position)
        }
    }

    private fun selectItem(holder: ViewHolder, idx: Int) {
        selectedItems.delete(idx)
        holder.itemView.text_inquiry_title.visibility = View.VISIBLE
        holder.itemView.text_inquiry_content.visibility = View.VISIBLE
        holder.itemView.group_btn.visibility = View.VISIBLE
        holder.itemView.text_inquiry_answer.visibility = View.VISIBLE
    }

    private fun clearItem(holder: ViewHolder, idx: Int) {
        selectedItems.put(idx, true)
        holder.itemView.text_inquiry_title.visibility = View.VISIBLE
        holder.itemView.text_inquiry_content.visibility = View.GONE
        holder.itemView.group_btn.visibility = View.GONE
        holder.itemView.text_inquiry_answer.visibility = View.GONE
//        notifyDataSetChanged()
    }

    private fun removeItem(idx: Int) {
        selectedItems.put(idx, false)
        data.removeAt(idx)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: InquiryItemBinding): RecyclerView.ViewHolder(binding.root) {
    }
}