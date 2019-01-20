package org.emptybit.luppy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LadiesItemsActivity extends AppCompatActivity {

    Button xWomenDress, xWomenJeans, xWomenTShirt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladies_items);

        Toolbar toolbar = findViewById(R.id.ladies_items_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        xWomenDress = findViewById(R.id.ladies_items_dresses);
        xWomenJeans = findViewById(R.id.ladies_items_jeans);
        xWomenTShirt = findViewById(R.id.ladies_items_tshirts);

        xWomenDress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
            }
        });

        xWomenJeans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
            }
        });

        xWomenTShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
            }
        });
    }
}
