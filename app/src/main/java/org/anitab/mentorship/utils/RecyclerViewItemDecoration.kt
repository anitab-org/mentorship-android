package org.anitab.mentorship.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecoration :
    RecyclerView.ItemDecoration() {
    private val decorationHeightWidth: Int = 10
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val itemPosition = parent.getChildAdapterPosition(view)
        val totalCount = parent.adapter!!.itemCount
        if (itemPosition >= 0 && itemPosition <= totalCount - 1) {
            outRect.bottom = decorationHeightWidth
        }
    }
}
