package org.emptybit.luppy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.emptybit.luppy.Adapters.ViewPagerAdapter;
import org.emptybit.luppy.Fragments.AdminExtrasItemsFragment;
import org.emptybit.luppy.Fragments.AdminMenItemsFragment;
import org.emptybit.luppy.Fragments.AdminWomenItemsFragment;

public class AdminDashboardActivity extends AppCompatActivity {

    private TabLayout xTab;
    private ViewPager xPager;
    private ViewPagerAdapter adapter;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Toolbar toolbar = findViewById(R.id.admin_toolbar);
        setSupportActionBar(toolbar);
        alertDialog = new AlertDialog.Builder(this);

        xTab = findViewById(R.id.admin_tab_layout);
        xPager = findViewById(R.id.admin_view_pager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new AdminWomenItemsFragment(), "");
        adapter.addFragment(new AdminMenItemsFragment(), "");
        adapter.addFragment(new AdminExtrasItemsFragment(), "");

        xPager.setAdapter(adapter);
        xTab.setupWithViewPager(xPager);

        xTab.getTabAt(0).setIcon(R.drawable.ic_women);
        xTab.getTabAt(1).setIcon(R.drawable.ic_man);
        xTab.getTabAt(2).setIcon(R.drawable.ic_extra);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        AdminDashboardActivity.this.getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
        } else if (id == R.id.action_order_history) {
            startActivity(new Intent(getApplicationContext(), OrderHistoryActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
