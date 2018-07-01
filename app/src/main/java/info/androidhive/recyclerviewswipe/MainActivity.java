package info.androidhive.recyclerviewswipe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnStartDragListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    public List<Item> cartList;
    private CartListAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback;
    // url to fetch menu json
    //private static final String URL = "https://api.androidhive.info/json/menu.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.my_cart));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        cartList = new ArrayList<>();
        Item i = new Item();
        i.setName("hello");
        i.setDescription("hello");
        i.setPrice(345);
        cartList.add(i);
        Item i1 = new Item();
        i1.setName("hello");
        i1.setDescription("hello");
        i1.setPrice(345);
        cartList.add(i1);
        Data();
        mAdapter = new CartListAdapter(this,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        // adding item touch helper1
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
         itemTouchHelperCallback = new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT,mAdapter);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        // making http call and fetching menu json
        //  prepareCart();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        new ItemTouchHelper(itemTouchHelperCallback).startDrag(viewHolder);
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
