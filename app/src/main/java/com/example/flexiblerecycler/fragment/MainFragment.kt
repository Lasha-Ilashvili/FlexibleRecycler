package com.example.flexiblerecycler.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.flexiblerecycler.adapter.ItemAdapter
import com.example.flexiblerecycler.base.BaseFragment
import com.example.flexiblerecycler.databinding.FragmentMainBinding
import com.example.flexiblerecycler.view_model.ItemViewModel
import kotlinx.coroutines.launch


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private lateinit var adapter: ItemAdapter

    private val viewModel: ItemViewModel by viewModels()


    override fun setRecycler() {
        adapter = ItemAdapter().apply {
            submitList(viewModel.itemFlow.value)
        }
        binding.rvItems.adapter = adapter
    }

    override fun setListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.addNewItem(3, "vax", "btnVax")
        }
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemFlow.collect {
                    adapter.submitList(it)
                }
            }
        }
    }
}