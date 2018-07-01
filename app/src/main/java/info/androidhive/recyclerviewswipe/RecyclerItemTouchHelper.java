package info.androidhive.recyclerviewswipe;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
/**
 * Created by ravi on 29/09/17.
 */
public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private ItemTouchHelperListener adapter;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs,ItemTouchHelperListener adapter) {
        super(dragDirs, swipeDirs);
        this.adapter=adapter;
    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        Log.d("VINAYONMOVE", "onchildrawmove");
        adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());

        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // We only want the active item to change
        Log.d("VINAYONMOVE", "onchildraw1");
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            Log.d("VINAYONMOVE", "onchildraw");
            if (viewHolder instanceof ItemTouchHelperViewHolder) {
                // Let the view holder know that this item is being moved or dragged
                ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
                itemViewHolder.onItemSelected();
            }
        }

        super.onSelectedChanged(viewHolder, actionState);
    }
//    @Override
//    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
//        if (viewHolder != null) {
//            Log.d("VINAY149149","view4");
//            final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;
//
//            getDefaultUIUtil().onSelected(foregroundView);
//        }
//    }

    //    @Override
//    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
//                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
//                                int actionState, boolean isCurrentlyActive) {
//        Log.d("VINAY149","view2");
//        final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;
//        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
//                actionState, isCurrentlyActive);
//    }
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.d("VINAY149", "view");
        final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }

        @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        Log.d("VINAY149","onchildraw");
        final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;

        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }
//    @Override
//    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            // Fade out the view as it is swiped out of the parent's bounds
//            final float alpha = 1.0f - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
//            viewHolder.itemView.setAlpha(alpha);
//            viewHolder.itemView.setTranslationX(dX);
//        } else {
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        }
//    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.onSwiped(viewHolder,direction,viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        Log.d("VINAYgetmove", "onchildraw");
        return makeMovementFlags(dragFlags, swipeFlags);
    }
//    @Override
//    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
//                          RecyclerView.ViewHolder target) {
//        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
//        return true;
//    }
//
//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
//    }

    public interface ItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
        boolean onItemMove(int fromPosition, int toPosition);
        void onItemDismiss(int position);
    }
    public interface ItemTouchHelperViewHolder {
        void onItemSelected();
    }

}
