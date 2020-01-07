package org.systers.mentorship.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class StatsItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.left = space
        outRect.top = space
        outRect.right = space
        outRect.bottom = space
    }
}
