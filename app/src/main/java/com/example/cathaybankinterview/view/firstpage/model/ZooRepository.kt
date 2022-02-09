package com.example.cathaybankinterview.view.firstpage.model

import com.example.cathaybankinterview.model.OpenDataService
import com.example.cathaybankinterview.model.api.getzooinfo.ZooRequestResult
import com.example.cathaybankinterview.view.firstpage.Venue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ZooRepository(private val service : OpenDataService) : IZooRepository {

    override fun getZooInfo(offset : Int, limit : Int, callback : IZooRepository.IOnZooResult){
        service.getZooInfo(limit, offset).enqueue(object : Callback<ZooRequestResult> {
            override fun onResponse(
                call: Call<ZooRequestResult>,
                response: Response<ZooRequestResult>
            ) {
                try {
                    val result = response.body()
                    val newList = result?.requestResult?.results?.map {
                        Venue(
                            category = it.eCategory.orEmpty(),
                            geo = it.eGeo.orEmpty(),
                            info = it.eInfo.orEmpty(),
                            memo = it.eMemo.orEmpty(),
                            name = it.eName.orEmpty(),
                            no = it.eNo.orEmpty(),
                            picUrl = it.ePicURL.orEmpty(),
                            id = it.id ?: -1,
                            webUrl = it.eURL.orEmpty()
                        )
                    } ?: error("取得動物園資訊錯誤")
                    callback.onSuccess(newList)
                } catch (exception : Exception){
                    callback.onFailure(exception)
                }
            }

            override fun onFailure(call: Call<ZooRequestResult>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

}