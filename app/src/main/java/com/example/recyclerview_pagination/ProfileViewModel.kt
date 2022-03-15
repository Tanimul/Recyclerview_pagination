package com.example.recyclerview_pagination


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    val repository: ProfileRepository
    val allWorkesCoroutine = MutableLiveData<List<ProfileModel>?>()
    init {
        repository = ProfileRepository()
    }

    fun getListCoroutine(page:Int,limit:Int) {
        viewModelScope.launch(Dispatchers.IO) {

            if (repository.getListCoroutine(page,limit).body() != null) {
                Log.d("fabniwfire", "getListCoroutine: " + repository.getListCoroutine(page,limit).body())
                allWorkesCoroutine.postValue(repository.getListCoroutine(page,limit).body())
            }


        }
    }

}