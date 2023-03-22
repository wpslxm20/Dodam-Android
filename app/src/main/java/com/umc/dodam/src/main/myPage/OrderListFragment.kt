package com.umc.dodam.src.main.myPage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.dodam.databinding.FragmentOrderListBinding
import com.umc.dodam.src.main.myPage.orderListRecycler.OrderListAdapter
import com.umc.dodam.src.main.myPage.orderListRecycler.OrderListItem

class OrderListFragment : Fragment() {

    private var _binding: FragmentOrderListBinding ?= null
    private val binding get() = _binding!!

    // RecyclerView와 Adapter 연결
    private lateinit var orderListAdapter: OrderListAdapter
    val orderList = mutableListOf<OrderListItem>()
    lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderListBinding.inflate(layoutInflater)

        orderList.apply {
            add(OrderListItem(10000, "CJ대한통운", 20000, 1))
            add(OrderListItem(12000, "CJ대한통운3", 20050, 2))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView와 Adapter 연결
        orderListAdapter = OrderListAdapter(this.orderList)
        binding.orderListRecyclerview.adapter = orderListAdapter
        mLayoutManager = LinearLayoutManager(requireContext())
        binding.orderListRecyclerview.layoutManager = mLayoutManager
    }

    // Fragment는 View 보다 더 오래 살아남기 때문에 한번 정리
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        private const val TAG = "OrderListFragment"
        fun instance() = OrderListFragment()
    }
}