package info.androidhive.recyclerviewswipe;

/**
 * Created by vinay_thakur on 6/30/2018.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}

