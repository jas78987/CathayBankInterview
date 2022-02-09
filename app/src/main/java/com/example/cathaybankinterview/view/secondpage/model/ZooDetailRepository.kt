package com.example.cathaybankinterview.view.secondpage.model

import com.example.cathaybankinterview.database.AppDatabase
import com.example.cathaybankinterview.database.entity.PlantDetailEntity
import com.example.cathaybankinterview.model.OpenDataService
import com.example.cathaybankinterview.model.api.getplant.PlantInfoRequest
import com.example.cathaybankinterview.model.api.getplant.PlantRequestResult
import com.example.cathaybankinterview.view.thirdpage.PlantDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ZooDetailRepository(private val service: OpenDataService, private val database: AppDatabase) :
    IZooDetailRepository {

    override fun getRelativePlant(
        placeName: String,
        callback: IZooDetailRepository.IOnPlantDetailResult
    ) {
        Thread{
            val plantDetailList = database.plantDetailDao().getAll().map { entity ->
                val locationList = entity.locationList.split(";")
                PlantDetail(
                    chineseName = entity.chineseName,
                    englishName = entity.englishName,
                    pictureUrl = entity.pictureUrl,
                    alias = entity.alias,
                    brief = entity.brief,
                    feature = entity.feature,
                    functionAndApplication = entity.functionAndApplication,
                    updateTime = entity.updateTime,
                    locationList = locationList
                )
            }

            if (plantDetailList.isEmpty()) {
                service.getPlant(1000, 0).enqueue(object : Callback<PlantInfoRequest> {
                    override fun onResponse(
                        call: Call<PlantInfoRequest>,
                        response: Response<PlantInfoRequest>
                    ) {
                        try {
                            val result = response.body()
                            val entityList = result?.plantRequestResult?.results?.mapNotNull {
                                PlantDetailEntity(
                                    id = it.id ?: return@mapNotNull null,
                                    chineseName = it.fNameCh ?: return@mapNotNull null,
                                    englishName = it.fNameEn ?: return@mapNotNull null,
                                    pictureUrl = it.fPic01URL ?: return@mapNotNull null,
                                    alias = it.fAlsoKnown ?: return@mapNotNull null,
                                    brief = it.fBrief ?: return@mapNotNull null,
                                    feature = it.fFeature ?: return@mapNotNull null,
                                    functionAndApplication = it.fFunctionApplication
                                        ?: return@mapNotNull null,
                                    updateTime = it.fUpdate ?: return@mapNotNull null,
                                    locationList = it.fLocation ?: return@mapNotNull null
                                )
                            }
                            if (entityList != null && entityList.isNotEmpty()) {
                                database.plantDetailDao().insertAll(entityList)
                            }

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
                            } ?: error("取得相關植物資訊錯誤")
                            callback.onSuccess(newList.filter { it.locationList.contains(placeName) }
                                .distinctBy { it.chineseName })
                        } catch (exception: Exception) {
                            callback.onFailure(exception)
                        }

                    }

                    override fun onFailure(call: Call<PlantInfoRequest>, t: Throwable) {
                        callback.onFailure(t)
                    }

                })
            } else {
                callback.onSuccess(plantDetailList)
            }
        }.start()
    }
}