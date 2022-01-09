package com.example.practicedeliveryheros.data.network

import com.example.practicedeliveryheros.data.response.address.AddressInfoResponse
import com.example.practicedeliveryheros.data.url.Key
import com.example.practicedeliveryheros.data.url.Url
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MapApiService {

//    @GET(Url.GET_TMAP_POIS)
//    suspend fun getSearchLocation(
//        @Header("appKey") appKey: String = Key.TMAP_API,
//        @Query("version") version: Int = 1,
//        @Query("callback") callback: String? = null,
//        @Query("count") count: Int = 20,
//        @Query("searchKeyword") keyword: String? = null,
//        @Query("areaLLCode") areaLLCode: String? = null,
//        @Query("areaLMCode") areaLMCode: String? = null,
//        @Query("resCoordType") resCoordType: String? = null,
//        @Query("searchType") searchType: String? = null,
//        @Query("multiPoint") multiPoint: String? = null,
//        @Query("searchtypCd") searchtypCd: String? = null,
//        @Query("radius") radius: String? = null,
//        @Query("reqCoordType") reqCoordType: String? = null,
//        @Query("centerLon") centerLon: String? = null,
//        @Query("centerLat") centerLat: String? = null
//    ): Response<SearchResponse>
//
//    @GET(Url.GET_TMAP_POIS_AROUND)
//    suspend fun getSearchLocationAround(
//        @Header("appKey") appKey: String = Key.TMAP_API,
//        @Query("version") version: Int = 1,
//        @Query("categories") categories: String? = null,
//        @Query("callback") callback: String? = null,
//        @Query("count") count: Int = 20,
//        @Query("searchKeyword") keyword: String? = null,
//        @Query("areaLLCode") areaLLCode: String? = null,
//        @Query("areaLMCode") areaLMCode: String? = null,
//        @Query("resCoordType") resCoordType: String? = null,
//        @Query("searchType") searchType: String? = null,
//        @Query("multiPoint") multiPoint: String? = null,
//        @Query("searchtypCd") searchtypCd: String? = null,
//        @Query("radius") radius: String? = null,
//        @Query("reqCoordType") reqCoordType: String? = null,
//        @Query("centerLon") centerLon: String? = null,
//        @Query("centerLat") centerLat: String? = null
//    ): Response<SearchResponse>

    @GET(Url.GET_TMAP_REVERSE_GEO_CODE) // API 요청 주소를 Get Method를 이용해서 매핑
    suspend fun getReverseGeoCode(
        @Header("appKey") appKey: String = Key.TMAP_API,
        @Query("version") version: Int = 1,
        @Query("callback") callback: String? = null,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("coordType") coordType: String? = null,
        @Query("addressType") addressType: String? = null
    ): Response<AddressInfoResponse>
}
