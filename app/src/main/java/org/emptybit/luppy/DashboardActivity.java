package org.emptybit.luppy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    public static String SELECTED_ITEM = "";
    Button xLadiesItems, xGentsItems, xExtras;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);

        alertDialog = new AlertDialog.Builder(this);

        xLadiesItems = findViewById(R.id.dashboard_ladies_items);
        xGentsItems = findViewById(R.id.dashboard_gents_items);
        xExtras = findViewById(R.id.dashboard_extra_items);

        xLadiesItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardActivity.SELECTED_ITEM = "Ladies Items";
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
            }
        });

        xGentsItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardActivity.SELECTED_ITEM = "Gents Items";
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
            }
        });

        xExtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardActivity.SELECTED_ITEM = "Extra";
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        DashboardActivity.this.getMenuInflater().inflate(R.menu.sign_out, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sign_out) {
            alertDialog.setTitle("Sign out");
            alertDialog.setMessage("Are you sure ?");
            alertDialog.setPositiveButton("Sign out", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
