package com.umc.dodam.src.main.myPage.orderListRecycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.dodam.R
import com.umc.dodam.databinding.OrderListItemBinding


class OrderListAdapter(
    val data: List<OrderListItem>
    ): RecyclerView.Adapter<OrderListAdapter.ViewHolder>(){

    lateinit var binding: OrderListItemBinding
    // 배달 진행 단계
    var deliverStep = 2

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_list_item, parent, false)
        binding = OrderListItemBinding.bind(inflatedView)
        return OrderListAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderListAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.apply {
            binding.orderNumber.text = item.orderNum.toString()
            binding.courierService.text = item.courierService
            binding.invoiceNumber.text = item.invoiceNum.toString()
            deliverStep = item.deliverStep
        }
        // 현재 배송 단계에 맞는 textview 컬러 변경
        when(deliverStep) {
            // 집하
            1 -> {
                binding.textCollection.setBackgroundResource(R.drawable.ic_gradation_circle)
                binding.textCollection.setTextColor(Color.parseColor("#FFFFFFFF"))
            }
            // 상품 이동 중
            2 -> {
                binding.textItemMoving.setBackgroundResource(R.drawable.ic_gradation_circle)
                binding.textItemMoving.setTextColor(Color.parseColor("#FFFFFFFF"))
            }
            // 배송지 도착
            3 -> {
                binding.textItemArrive.setBackgroundResource(R.drawable.ic_gradation_circle)
                binding.textItemArrive.setTextColor(Color.parseColor("#FFFFFFFF"))
            }
            // 배송 출발
            4 -> {
                binding.textItemDepart.setBackgroundResource(R.drawable.ic_gradation_circle)
                binding.textItemDepart.setTextColor(Color.parseColor("#FFFFFFFF"))
            }
            // 배송 완료
            5 -> {
                binding.textDeliverComplete.setBackgroundResource(R.drawable.ic_gradation_circle)
                binding.textDeliverComplete.setTextColor(Color.parseColor("#FFFFFFFF"))
            }
        }
    }

    class ViewHolder(val binding: OrderListItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

}