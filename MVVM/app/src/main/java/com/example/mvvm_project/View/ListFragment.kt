package com.example.mvvm_project.View

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_project.View.adaptadores.AdapterItem
import com.example.mvvm_project.ViewModel.ApiStatus
import com.example.mvvm_project.ViewModel.PokeViewModel
import com.example.mvvm_project.databinding.FragmentListBinding
import com.example.mvvm_project.domain.Details
import com.example.mvvm_project.domain.SelectedListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ClassCastException

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PokeViewModel by viewModels()
    private lateinit var adapter: AdapterItem
    private lateinit var recyclerView: RecyclerView

    private lateinit var listener: SelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try {
            context as SelectedListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context debe implementar el listener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // val viewModel2 = ViewModelProvider(this)[PokeViewModel::class.java]
        adapter = AdapterItem()
        recyclerView = binding.recyclerViewPoke
        recyclerView.adapter = adapter

        observeApiStatus()
        observeListPokemon()
        onClickItem()
    }

    private fun observeApiStatus() {
        viewModel.status.observe(viewLifecycleOwner) { status->
            when (status) {
                ApiStatus.LOADING -> {
                    binding.statusOffline.visibility = View.GONE
                    binding.shimmerLoading.visibility = View.VISIBLE
                    binding.recyclerViewPoke.visibility =View.GONE
                }
                ApiStatus.ERROR -> {
                    binding.statusOffline.visibility = View.VISIBLE
                    binding.shimmerLoading.visibility = View.GONE
                    binding.recyclerViewPoke.visibility =View.GONE
                }
                ApiStatus.DONE -> {
                    binding.statusOffline.visibility = View.GONE
                    binding.shimmerLoading.visibility = View.GONE
                    binding.recyclerViewPoke.visibility =View.VISIBLE
                }
            }
        }
    }

    private fun observeListPokemon() {
        viewModel.pokelist.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun onClickItem() {
        adapter.onClickItem = { poke ->

            listener.onSelected(poke.id)
        }
    }

    private fun test(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("tag", "llamada...")
            val res = Details().fromPoke(id)
            Log.d("tag", "$res")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}