package org.emptybit.luppy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LadiesItemsActivity extends AppCompatActivity {

    public static String SELECTED_ITEM = "";
    Button xWomenDress, xWomenJeans, xWomenTShirt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladies_items);

        xWomenDress = findViewById(R.id.ladies_items_dresses);
        xWomenJeans = findViewById(R.id.ladies_items_jeans);
        xWomenTShirt = findViewById(R.id.ladies_items_tshirts);

        xWomenDress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SELECTED_ITEM = "Women Dress";
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
            }
        });

        xWomenJeans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SELECTED_ITEM = "Women Jeans";
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
            }
        });

        xWomenTShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SELECTED_ITEM = "Women T-Shirt";
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
            }
        });
    }
}
