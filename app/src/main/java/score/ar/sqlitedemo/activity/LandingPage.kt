package score.ar.sqlitedemo.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import score.ar.memoboard.retrofit.Constant
import score.ar.memoboard.retrofit.RetrofitError
import score.ar.memoboard.retrofit.RetrofitUtil
import score.ar.sqlitedemo.R
import score.ar.sqlitedemo.adapter.RVAdapter
import score.ar.sqlitedemo.retrofit.ApiResponse

class LandingPage : AppCompatActivity() {

    var rvDemo: RecyclerView? = null
    var rvAdapter : RVAdapter? = null
    lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        initialize()

        serviceCall()

    }

    private fun serviceCall() {
        val service = RetrofitUtil.retrofit(Constant.BASE_URL)
        val call = service.getDemoValue()

        var pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#E6720C")
        pDialog.titleText = "Please wait"
        pDialog.setCancelable(false)
        pDialog.show()

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                pDialog.dismiss()

                try {
                    var apiResponse = response.body()

                    rvAdapter = RVAdapter(this@LandingPage,apiResponse)

                    mLayoutManager = LinearLayoutManager(this@LandingPage)
                    rvDemo?.layoutManager = mLayoutManager
                    rvDemo?.setHasFixedSize(true)
                    rvDemo?.adapter = rvAdapter


                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                t.printStackTrace()
                pDialog.dismiss()
                val str = RetrofitError.showErrorMessage(t)
                Log.e("serviceCallToLoadData", str)
            }
        })
    }

    private fun initialize() {
        rvDemo = findViewById(R.id.rvDemo)
    }
}
