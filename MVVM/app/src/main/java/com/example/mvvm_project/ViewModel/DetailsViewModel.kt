package com.example.mvvm_project.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_project.Model.PokeItemDetails
import com.example.mvvm_project.View.DetailFragment
import com.example.mvvm_project.domain.Details
import kotlinx.coroutines.launch


enum class ApiStatusDetail {LOADING, ERROR, DONE}

class DetailsViewModel(): ViewModel() {
    private var _pokeDetails = MutableLiveData<PokeItemDetails>()
    val pokeDetails: LiveData<PokeItemDetails>
        get() = _pokeDetails

    private var _status = MutableLiveData<ApiStatusDetail>()
    val status: LiveData<ApiStatusDetail>
    get() = _status

    init {
        PokeDetails(DetailFragment.idP)
    }

    private fun PokeDetails(id: Int){
        _status.value = ApiStatusDetail.LOADING
        viewModelScope.launch {
            try {
                _pokeDetails.value = Details().fromPoke(id)
                _status.value = ApiStatusDetail.DONE
            }
            catch (e:Exception){
                _status.value = ApiStatusDetail.ERROR
                Log.d("Poke Details error", "${e.message}")
            }
        }
    }
}