package score.ar.memoboard.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtil {
  fun retrofit(url: String): RestInterface {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
      .connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .writeTimeout(60, TimeUnit.SECONDS)
      .addInterceptor(interceptor)
      .build()


    val service: RestInterface
    val retrofit = Retrofit.Builder()
      .baseUrl(url)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())

      .build()
    service = retrofit.create(RestInterface::class.java)

    return service
  }
}