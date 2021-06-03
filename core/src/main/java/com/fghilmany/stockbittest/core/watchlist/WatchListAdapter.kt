package com.fghilmany.stockbittest.core.watchlist

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fghilmany.stockbittest.core.R
import com.fghilmany.stockbittest.core.data.source.remote.response.DataItem
import com.fghilmany.stockbittest.core.databinding.ItemWatchlistBinding
import kotlin.math.pow
import kotlin.math.roundToInt

class WatchListAdapter: RecyclerView.Adapter<WatchListAdapter.ViewHolder>() {

    private val listWatchList = ArrayList<DataItem>()

    fun setList(list: List<DataItem>?){
        if (list == null) return
        listWatchList.clear()
        listWatchList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_watchlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = listWatchList[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int = listWatchList.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemWatchlistBinding.bind(view)
        @SuppressLint("SetTextI18n")
        fun bind(result: DataItem) {
            with(binding){
                result.apply {
                    val price = (rAW?.uSD?.pRICE?:0.0)
                    val median =(rAW?.uSD?.mEDIAN?:0.0)

                    val now = median - price
                    val percent = now/price*100

                    tvName.text = coinInfo?.name
                    tvFullName.text = coinInfo?.fullName
                    tvPrice.text = price.toString()

                    when {
                        now > 0 -> {
                            tvStat.setTextColor(ContextCompat.getColor(itemView.context, R.color.green_600))
                        }
                        now < 0 -> {
                            tvStat.setTextColor(ContextCompat.getColor(itemView.context, R.color.red_600))
                        }
                        else -> {
                            tvStat.setTextColor(ContextCompat.getColor(itemView.context, R.color.grey_600))
                        }
                    }

                    tvStat.text = "${getSimpleDecimal(now)} (${getSimpleDecimal(percent)}%)"

                }
            }
        }

        fun getSimpleDecimal(value: Double): Double{
            val sinificantNumber = 2
            val temp = 10.0.pow(sinificantNumber.toDouble())
            val result = (value * temp).roundToInt().toDouble() / temp
            return result
        }

    }
}