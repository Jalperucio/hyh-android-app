package com.hiberus.handh.data.feature.locations.datastore.remote

import com.hiberus.handh.data.feature.locations.datastore.remote.model.RemoteRickAndMortyLocation
import com.hiberus.handh.data.feature.services.remote.RickAndMortyService
import com.hiberus.handh.data.feature.services.remote.model.RemoteRickAndMortyResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationsService: RickAndMortyService {
    @GET("api/location")
    suspend fun getAllLocations(
        @Query("page") currentPage: Int
    ): Response<RemoteRickAndMortyResult<RemoteRickAndMortyLocation>>

    @GET("api/location")
    suspend fun getFilteredLocations(
        @Query("page") currentPage: Int,
        @Query("name") nameFilter: String = "",
        @Query("type") type: String = "",
        @Query("dimension") dimension: String = ""
    ): Response<RemoteRickAndMortyResult<RemoteRickAndMortyLocation>>

    @GET("api/location/{locationId}")
    suspend fun getLocationById(
        @Path("locationId") locationId: Int
    ): Response<RemoteRickAndMortyLocation>

    @GET("api/location/{locationsId}")
    suspend fun getDiscreteLocations(
        @Query("page") currentPage: Int,
        @Path("locationsId",encoded = false)
        locationsId: String
    ): Response<List<RemoteRickAndMortyLocation>>
}