package org.emptybit.luppy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    Button xLadiesItems, xGentsItems, xExtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        xLadiesItems = findViewById(R.id.dashboard_ladies_items);
        xGentsItems = findViewById(R.id.dashboard_gents_items);
        xExtras = findViewById(R.id.dashboard_extra_items);

        xLadiesItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LadiesItemsActivity.class));
            }
        });
    }
}
