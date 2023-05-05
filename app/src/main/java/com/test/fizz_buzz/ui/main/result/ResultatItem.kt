package com.test.fizz_buzz.ui.main.result

import android.annotation.SuppressLint
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.fizz_buzz.R
import com.test.fizz_buzz.utils.KauIItem

class ResultatItem(var data: String) : KauIItem<ResultatItem, ResultatItem.ViewHolder>(
    R.layout.resultat_item, { ViewHolder(it) }, R.id.fastadapter_listitem_id
) {
    private var lastPosition = -1

    @SuppressLint("SetTextI18n")
    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.result.text = data
        setAnimation(holder.itemView, holder.adapterPosition)
    }

    override fun unbindView(holder: ViewHolder) {}

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var result = itemView.findViewById(R.id.result) as TextView
    }

    private fun setAnimation(view: View, bindingAdapterPosition: Int) {
        val animation = AnimationUtils.loadAnimation(view.context, android.R.anim.fade_in)
        view.startAnimation(animation)
        lastPosition = bindingAdapterPosition
    }
}