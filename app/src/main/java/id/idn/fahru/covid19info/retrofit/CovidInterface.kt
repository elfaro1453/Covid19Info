package id.idn.fahru.covid19info.retrofit

import id.idn.fahru.covid19info.pojo.ResponseSummary
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Imam Fahrur Rofi on 30/07/2020.
 */
interface CovidInterface {

    // Path Url yang dituju adalah https://api.covid19api.com/summary,
    // karena base url-nya https://api.covid19api.com/
    // maka hanya perlu menulis summary saja
    @GET("summary")
    // response berupa data class dari pojo ResponseSummary
    suspend fun getSummary(): Response<ResponseSummary>
}
