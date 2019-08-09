package score.ar.sqlitedemo.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class EmpWiseList {
    @SerializedName("memoFromId")
    @Expose
    private var memoFromId: Int? = null
    @SerializedName("memoFromName")
    @Expose
    private var memoFromName: String? = null
    @SerializedName("totalCount")
    @Expose
    private var totalCount: Int? = null

    fun getMemoFromId(): Int? {
        return memoFromId
    }

    fun setMemoFromId(memoFromId: Int?) {
        this.memoFromId = memoFromId
    }

    fun getMemoFromName(): String? {
        return memoFromName
    }

    fun setMemoFromName(memoFromName: String) {
        this.memoFromName = memoFromName
    }

    fun getTotalCount(): Int? {
        return totalCount
    }

    fun setTotalCount(totalCount: Int?) {
        this.totalCount = totalCount
    }
}