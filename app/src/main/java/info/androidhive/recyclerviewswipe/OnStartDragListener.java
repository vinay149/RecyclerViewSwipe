package info.androidhive.recyclerviewswipe;

import android.support.v7.widget.RecyclerView;

/**
 * Created by vinay_thakur on 6/30/2018.
 */

public interface OnStartDragListener {

    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);

}
