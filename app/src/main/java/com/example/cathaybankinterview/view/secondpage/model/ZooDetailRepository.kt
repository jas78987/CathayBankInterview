package com.example.cathaybankinterview.view.secondpage.model

import com.example.cathaybankinterview.model.OpenDataService
import com.example.cathaybankinterview.model.api.getplant.PlantInfoRequest
import com.example.cathaybankinterview.model.api.getplant.PlantRequestResult
import com.example.cathaybankinterview.view.thirdpage.PlantDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ZooDetailRepository(private val service : OpenDataService) : IZooDetailRepository {

    override fun getRelativePlant(
        placeName: String,
        callback: IZooDetailRepository.IOnPlantDetailResult
    ) {
        service.getPlant(1000,0).enqueue(object : Callback<PlantInfoRequest> {
            override fun onResponse(
                call: Call<PlantInfoRequest>,
                response: Response<PlantInfoRequest>
            ) {
                try{
                    val result = response.body()
                    val newList = result?.plantRequestResult?.results?.map {
                        val locationList = it.fLocation?.split(";").orEmpty()
                        PlantDetail(
                            chineseName = it.fNameCh.orEmpty(),
                            englishName = it.fNameEn.orEmpty(),
                            pictureUrl = it.fPic01URL.orEmpty(),
                            alias = it.fAlsoKnown.orEmpty(),
                            brief = it.fBrief.orEmpty(),
                            feature = it.fFeature.orEmpty(),
                            functionAndApplication = it.fFunctionApplication.orEmpty(),
                            updateTime = it.fUpdate.orEmpty(),
                            locationList = locationList
                        )
                    } ?: error("")
                    callback.onSuccess(newList.filter { it.locationList.contains(placeName) }.distinctBy { it.chineseName })
                }catch (exception : Exception){
                    callback.onFailure(exception)
                }

            }

            override fun onFailure(call: Call<PlantInfoRequest>, t: Throwable) {
                callback.onFailure(t)
            }

        })
    }
}