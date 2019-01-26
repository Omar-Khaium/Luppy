package org.emptybit.luppy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.emptybit.luppy.Adapters.CartAdapter;
import org.emptybit.luppy.Models.CartModel;

import java.util.Iterator;

import static org.emptybit.luppy.ShopActivity.cart;

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

        xCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xTotalAmount.getText().toString().equals("0")) {
                    Snackbar.make(view, "Cart is empty", Snackbar.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getApplicationContext(), CheckoutActivity.class).putExtra("Amount", xTotalAmount.getText().toString()));
                }
            }
        });

        for (Iterator<CartModel> cartModel = cart.iterator(); cartModel.hasNext(); ) {
            CartModel cartModel1 = cartModel.next();
            if (cartModel1.getQuantity() == 0) {
                cart.remove(cartModel1);
            }
        }
        CartAdapter adapter = new CartAdapter(getApplicationContext());
        xListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }
}
