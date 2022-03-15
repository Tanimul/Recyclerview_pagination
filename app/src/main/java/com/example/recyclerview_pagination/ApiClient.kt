package com.example.recyclerview_pagination

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    companion object {
        var retrofit: Retrofit? = null
        lateinit var mainInterface: MainInterface
        val BASEURL = "https://picsum.photos/"
        fun getClient(): MainInterface {
            if (retrofit == null) {
                synchronized(this) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASEURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    mainInterface = retrofit!!.create(MainInterface::class.java)
                }


            }
            return mainInterface
        }
    }

}