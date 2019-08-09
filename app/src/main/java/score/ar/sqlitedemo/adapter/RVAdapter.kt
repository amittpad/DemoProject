package score.ar.sqlitedemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import score.ar.sqlitedemo.R
import score.ar.sqlitedemo.activity.LandingPage
import score.ar.sqlitedemo.retrofit.ApiResponse

class RVAdapter(
    internal var landingPage: LandingPage,
    internal var apiResponse: ApiResponse?
) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return apiResponse?.getEmpWiseList()?.size!!
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.slNo.text = apiResponse?.getEmpWiseList()?.get(p1)?.getMemoFromName()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var slNo: TextView = itemView.findViewById(R.id.slNo) as TextView
    }
}