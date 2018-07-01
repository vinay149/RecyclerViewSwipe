package info.androidhive.recyclerviewswipe;

/**
 * Created by ravi on 26/09/17.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> implements RecyclerItemTouchHelper.ItemTouchHelperListener{
    private Context context;
    private final OnStartDragListener mDragStartListener;
    private ArrayList<Item> cartList=new ArrayList<Item>();
    FrameLayout frame;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, price;
        public ImageView thumbnail;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            price = view.findViewById(R.id.price);
            thumbnail = view.findViewById(R.id.thumbnail);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }


    public CartListAdapter(Context context, OnStartDragListener onStartDragListener) {
        this.mDragStartListener = onStartDragListener;
        Item i = new Item();
        i.setName("hello");
        i.setDescription("hello");
        i.setPrice(345);
        cartList.add(i);
        Item i1 = new Item();
        i1.setName("hello");
        i1.setDescription("hello");
        i1.setPrice(346);
        Data();
        cartList.add(i1);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_list_item, parent, false);
        frame=(FrameLayout)itemView.findViewById(R.id.framelayout);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Item item = cartList.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.price.setText("₹" + item.getPrice());
        holder.thumbnail.setImageResource(R.drawable.ic_launcher_background);
        holder.thumbnail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }
    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void removeItem(int position) {
        cartList.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onItemDismiss(int position) {
        cartList.remove(position);
        notifyItemRemoved(position);
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        Log.d("hellovinay", "swipe");
        if (viewHolder instanceof CartListAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = cartList.get(viewHolder.getAdapterPosition()).getName();
            // backup of removed item for undo purpose
            final Item deletedItem = cartList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            // remove the item from recycler view
            onItemDismiss(viewHolder.getAdapterPosition());
            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(frame, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(cartList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(cartList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public void restoreItem(Item item, int position) {
        cartList.add(position, item);
        notifyItemInserted(position);
    }
    public  void Data()
    {
        Item item=new Item("Samosha","The price of samosha is this", (double) 991,"");
        cartList.add(item);
        Item item1=new Item("Samosha","The price of samosha is this", (double) 992,"");
        cartList.add(item1);
        Item item2=new Item("Samosha","The price of samosha is this", (double) 993,"");
        cartList.add(item2);
        Item item3=new Item("Samosha","The price of samosha is this", (double) 994,"");
        cartList.add(item3);
        Item item4=new Item("Samosha","The price of samosha is this", (double) 995,"");
        cartList.add(item4);
        Item item5=new Item("Samosha","The price of samosha is this", (double) 996,"");
        cartList.add(item5);
        Item item6=new Item("Samosha","The price of samosha is this", (double) 997,"");
        cartList.add(item6);

    }

}
