package com.example.recyclerview_pagination

import android.util.Log

import retrofit2.Response
import kotlin.math.log

class ProfileRepository() {
    private val TAG: String = "ProfileRepository"
    private val mainInterface: MainInterface = ApiClient.getClient()

    suspend fun getListCoroutine(page:Int,limit:Int): Response<List<ProfileModel>> {
        Log.d(TAG, "getList_Coroutine: "+mainInterface.getList(page,limit).body())
        return mainInterface.getList(page,limit)
    }

}