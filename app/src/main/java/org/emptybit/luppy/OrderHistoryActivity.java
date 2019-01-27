package org.emptybit.luppy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.emptybit.luppy.Adapters.OrderMainAdapter;
import org.emptybit.luppy.Models.OrderModel;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    RecyclerView xListView;
    ArrayList<OrderModel> arrayList;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        Toolbar toolbar = findViewById(R.id.order_history_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        xListView = findViewById(R.id.orders_list);
        xListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getData();
    }

    public int getData() {
        try {
            arrayList = new ArrayList<>();
            database = FirebaseDatabase.getInstance();
            database.getReference("orders").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        try {
                            arrayList.add(snapshot.getValue(OrderModel.class));
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    OrderMainAdapter adapter = new OrderMainAdapter(getApplicationContext(), arrayList);
                    xListView.setAdapter(adapter);

//                    xListView.setVisibility(View.VISIBLE);
//                    xShimmerLayout.setVisibility(View.GONE);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Database Exception : " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            return 1;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return 0;
        }
    }
}
