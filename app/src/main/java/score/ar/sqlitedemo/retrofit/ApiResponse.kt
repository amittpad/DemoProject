package score.ar.sqlitedemo.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ApiResponse {
    @SerializedName("status")
    @Expose
    private var status: Int? = null
    @SerializedName("empWiseList")
    @Expose
    private var empWiseList: List<EmpWiseList>? = null

    fun getStatus(): Int? {
        return status
    }

    fun setStatus(status: Int?) {
        this.status = status
    }

    fun getEmpWiseList(): List<EmpWiseList>? {
        return empWiseList
    }

    fun setEmpWiseList(empWiseList: List<EmpWiseList>) {
        this.empWiseList = empWiseList
    }
}