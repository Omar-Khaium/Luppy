package org.emptybit.luppy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.emptybit.luppy.Adapters.ShopAdapter;
import org.emptybit.luppy.Models.CartModel;
import org.emptybit.luppy.Models.CategoryModel;
import org.emptybit.luppy.Models.ProductModel;

import java.util.ArrayList;

import static org.emptybit.luppy.DashboardActivity.SELECTED_ITEM;

public class ShopActivity extends AppCompatActivity {

    public static ArrayList<CartModel> cart = new ArrayList<>();
    private RecyclerView xListView;
    private ShopAdapter adapter;
    private ArrayList<CategoryModel> categoryModels;
    private FloatingActionButton xCart;
    private AlertDialog.Builder alertBuilder;
    private AlertDialog alertDialog;
    private LinearLayout xShimmerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Toolbar toolbar = findViewById(R.id.shop_items_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        xListView = findViewById(R.id.shop_items_to_display_list);
        xShimmerLayout = findViewById(R.id.shimmer_shop_layout);
        xListView.setLayoutManager(new LinearLayoutManager(this));
        xCart = findViewById(R.id.shop_items_to_display_cart);

        alertBuilder = new AlertDialog.Builder(this);
        alertDialog = alertBuilder.create();

        switch (SELECTED_ITEM) {
            case "Ladies Items":
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorLadies));
                break;
            case "Gents Items":
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorGents));
                break;
            case "Extra":
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorExtras));
                break;
        }
        getData();

        xCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });

    }

    public int getData() {
        try {
            final ArrayList<ProductModel> arrayList = new ArrayList<>();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.getReference("products").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        try {
                            if (snapshot.getValue(ProductModel.class).getCategory().equals(SELECTED_ITEM)) {
                                arrayList.add(snapshot.getValue(ProductModel.class));
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    createCategory(arrayList);
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

    private void createCategory(ArrayList<ProductModel> arrayList) {
        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
        try {
            for (ProductModel product : arrayList) {
                if (categoryModels.size() != 0) {
                    for (CategoryModel category : categoryModels) {
                        if (category.getName().equals(product.getSub_category())) {
                            category.getProductModels().add(product);
                            break;
                        } else {
                            category = new CategoryModel(product.getSub_category(), new ArrayList<ProductModel>());
                            category.getProductModels().add(product);
                            categoryModels.add(category);
                            break;
                        }
                    }
                } else {
                    CategoryModel category = new CategoryModel(product.getSub_category(), new ArrayList<ProductModel>());
                    category.getProductModels().add(product);
                    categoryModels.add(category);
                }
            }

            adapter = new ShopAdapter(getApplicationContext(), categoryModels);
            xListView.setAdapter(adapter);

            xListView.setVisibility(View.VISIBLE);
            xShimmerLayout.setVisibility(View.GONE);
        } catch (Exception e) {
        }
    }
}
