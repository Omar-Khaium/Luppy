package org.emptybit.luppy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import org.emptybit.luppy.Adapters.CartAdapter;

public class CartActivity extends AppCompatActivity {

    public static TextView xTotalAmount;
    private RecyclerView xListView;
    private Button xCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = findViewById(R.id.cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        xListView = findViewById(R.id.cart_list);
        xListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        xTotalAmount = findViewById(R.id.cart_total);
        xTotalAmount.setText("0");

        xCheckout = findViewById(R.id.cart_checkout);

        CartAdapter adapter = new CartAdapter(getApplicationContext());
        xListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }
}
