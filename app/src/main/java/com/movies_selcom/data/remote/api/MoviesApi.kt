package com.movies_selcom.data.remote.api

import com.movies_selcom.model.response_models.MoviesModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface MoviesApi {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2NzQ4OGNmODYzZjJmNTZhNDY2Y2ZjYTY3NmNmZDc4ZCIsIm5iZiI6MTczMjY1NzIxNi40NzU1MTI1LCJzdWIiOiI2MWIzMDY2OTY3ZTBmNzAwODZkMmRmMzkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.WGX8RkTh9EcttstE_jr44F4lT_Z9NQIYtFc1lUVNEf4")
    @GET("/3/movie/popular")
    @Throws()
    suspend fun getPopularMovies(): MoviesModel

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2NzQ4OGNmODYzZjJmNTZhNDY2Y2ZjYTY3NmNmZDc4ZCIsIm5iZiI6MTczMjY1NzIxNi40NzU1MTI1LCJzdWIiOiI2MWIzMDY2OTY3ZTBmNzAwODZkMmRmMzkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.WGX8RkTh9EcttstE_jr44F4lT_Z9NQIYtFc1lUVNEf4")
    @GET("/3/movie/now_playing")
    @Throws()
    suspend fun getBroadcastMovies(): MoviesModel
}
