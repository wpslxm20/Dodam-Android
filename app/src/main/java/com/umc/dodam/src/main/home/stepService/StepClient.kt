package com.umc.dodam.src.main.home.stepService

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 싱글톤으로 구현하기 위해 object 키워드 사용
object StepClient {
    private var instance: Retrofit? = null
    private const val CONNECT_TIMEOUT_SEC = 20000L
    private const val BASE_URL = "https://43.200.255.191/"

    fun getInstance() : Retrofit{
        if(instance == null) {

            // 로깅인터셉터 세팅
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            // OKHttpClient에 로깅인터셉터 등록
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .build()

            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return instance!!
    }
}

//class StepClient {
//    companion object {
//        private const val BASE_URL = "https://43.200.255.191/"
//        fun getApi(): StepServices = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(OkHttpClient())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(StepServices::class.java)
//    }
//}
//
//data class StepResponse (
//    @SerializedName("stepid") var stepId: Int
//)
//
//data class StepRequest (
//    @SerializedName("stepID") var stepid: Int,
//    @SerializedName("stepName") var stepname: String,
//    @SerializedName("startDate") var startdate: String,
//    @SerializedName("endDate") var enddate: String
//)
//
//interface StepServices {
//    @GET("step/select")
//    fun getStepId(
//    ): Call<StepResponse>
//
////    @PUT("step/select")
////    fun putStep(
////    ): Call<Request> (@)
//}