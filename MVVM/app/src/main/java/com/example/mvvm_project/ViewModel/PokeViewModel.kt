package com.example.mvvm_project.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_project.Model.ItemPoke
import com.example.mvvm_project.domain.Pokemons
import kotlinx.coroutines.launch

enum class ApiStatus {LOADING, ERROR, DONE}

class PokeViewModel : ViewModel() {
    private val _pokeList = MutableLiveData<List<ItemPoke>>()
    val pokelist: LiveData<List<ItemPoke>>
    get() = _pokeList

    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
    get() = _status

    init {
        AllofIt()
    }

    fun AllofIt(){
        _status.value = ApiStatus.LOADING
        viewModelScope.launch {
            try {
                _pokeList.value = Pokemons().AllofIt()
                _status.value = ApiStatus.DONE
            }
            catch (e: java.lang.Exception){
                _status.value = ApiStatus.ERROR
                Log.d("Error all of it", "${e.message}")
                _pokeList.value = listOf()
            }
        }
    }
}