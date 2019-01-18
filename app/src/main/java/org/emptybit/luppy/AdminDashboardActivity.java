package org.emptybit.luppy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    private TabLayout xTab;
    private ViewPager xPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

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
}
