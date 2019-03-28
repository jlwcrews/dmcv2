package no.jlwcrews.dmcv2.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup

class ExpansionLookup(private val rv: RecyclerView): ItemDetailsLookup<Long>() {

    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {

        val view = rv.findChildViewUnder(event.x, event.y)
        if(view != null) {
            return (rv.getChildViewHolder(view) as ExpansionListAdapter.ExpansionViewHolder).getExpansionDetails()
        }
        return null

    }
}