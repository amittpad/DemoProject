package score.ar.memoboard.retrofit

import retrofit2.Call
import retrofit2.http.GET
import score.ar.sqlitedemo.retrofit.ApiResponse


interface RestInterface {

    @GET(Constant.DEMO)
    fun getDemoValue(): Call<ApiResponse>


    /*@GET(Constant.DEMO)
    fun getDrawerValues(
        @Query("userId") userId: String
    ): Call<DrawerResponse>*/



}

