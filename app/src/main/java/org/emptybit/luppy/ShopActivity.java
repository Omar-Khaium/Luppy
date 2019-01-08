package org.emptybit.luppy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    private RecyclerView xListView;
    private ShopAdapter adapter;
    private ArrayList<CategoryModel> categoryModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Toolbar toolbar = findViewById(R.id.shop_items_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        xListView = findViewById(R.id.shop_items_to_display);
        xListView.setLayoutManager(new LinearLayoutManager(this));

        switch (DashboardActivity.SELECTED_ITEM) {
            case "Extra":
                adapter = new ShopAdapter(getApplicationContext(), getExtras());
                break;
        }

        xListView.setAdapter(adapter);
    }

    private ArrayList<CategoryModel> getExtras() {
        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
        categoryModels.add(new CategoryModel("Buttons", getButtons()));
        categoryModels.add(new CategoryModel("Embroidery", getEmbroidery()));
        categoryModels.add(new CategoryModel("Lace Belts", getLaceBelts()));
        categoryModels.add(new CategoryModel("Lace", getLaces()));
        categoryModels.add(new CategoryModel("Zippers", getZippers()));
        return categoryModels;
    }

    private ArrayList<ProductModel> getButtons() {
        ArrayList<ProductModel> productModels = new ArrayList<>();
        productModels.add(new ProductModel(R.drawable.button1, 20));
        productModels.add(new ProductModel(R.drawable.button2, 20));
        productModels.add(new ProductModel(R.drawable.button3, 20));
        productModels.add(new ProductModel(R.drawable.button4, 20));
        productModels.add(new ProductModel(R.drawable.button5, 20));
        productModels.add(new ProductModel(R.drawable.button6, 20));
        productModels.add(new ProductModel(R.drawable.button7, 20));
        productModels.add(new ProductModel(R.drawable.button8, 20));
        productModels.add(new ProductModel(R.drawable.button9, 20));
        productModels.add(new ProductModel(R.drawable.button10, 20));
        productModels.add(new ProductModel(R.drawable.button11, 20));
        productModels.add(new ProductModel(R.drawable.button12, 20));
        productModels.add(new ProductModel(R.drawable.button13, 20));
        productModels.add(new ProductModel(R.drawable.button14, 20));
        productModels.add(new ProductModel(R.drawable.button15, 20));
        productModels.add(new ProductModel(R.drawable.button16, 20));
        productModels.add(new ProductModel(R.drawable.button17, 20));
        productModels.add(new ProductModel(R.drawable.button18, 20));
        productModels.add(new ProductModel(R.drawable.button19, 20));
        productModels.add(new ProductModel(R.drawable.button21, 20));
        productModels.add(new ProductModel(R.drawable.button22, 20));
        productModels.add(new ProductModel(R.drawable.button23, 20));
        return productModels;
    }

    private ArrayList<ProductModel> getEmbroidery() {
        ArrayList<ProductModel> productModels = new ArrayList<>();
        productModels.add(new ProductModel(R.drawable.embroidery1, 40));
        productModels.add(new ProductModel(R.drawable.embroidery2, 20));
        productModels.add(new ProductModel(R.drawable.embroidery3, 60));
        productModels.add(new ProductModel(R.drawable.embroidery4, 50));
        productModels.add(new ProductModel(R.drawable.embroidery5, 70));
        productModels.add(new ProductModel(R.drawable.embroidery6, 20));
        productModels.add(new ProductModel(R.drawable.embroidery7, 80));
        productModels.add(new ProductModel(R.drawable.embroidery8, 20));
        productModels.add(new ProductModel(R.drawable.embroidery9, 80));
        productModels.add(new ProductModel(R.drawable.embroidery10, 20));
        productModels.add(new ProductModel(R.drawable.embroidery11, 70));
        productModels.add(new ProductModel(R.drawable.embroidery12, 20));
        productModels.add(new ProductModel(R.drawable.embroidery13, 50));
        productModels.add(new ProductModel(R.drawable.embroidery15, 80));
        productModels.add(new ProductModel(R.drawable.embroidery16, 80));
        productModels.add(new ProductModel(R.drawable.embroidery17, 20));
        productModels.add(new ProductModel(R.drawable.embroidery18, 20));
        return productModels;
    }

    private ArrayList<ProductModel> getLaceBelts() {
        ArrayList<ProductModel> productModels = new ArrayList<>();
        productModels.add(new ProductModel(R.drawable.lace_belts1, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts2, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts3, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts4, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts5, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts6, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts7, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts8, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts9, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts10, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts11, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts12, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts13, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts14, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts15, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts16, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts17, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts18, 20));
        productModels.add(new ProductModel(R.drawable.lace_belts19, 20));
        return productModels;
    }

    private ArrayList<ProductModel> getLaces() {
        ArrayList<ProductModel> productModels = new ArrayList<>();
        productModels.add(new ProductModel(R.drawable.lace1, 40));
        productModels.add(new ProductModel(R.drawable.lace2, 20));
        productModels.add(new ProductModel(R.drawable.lace3, 60));
        productModels.add(new ProductModel(R.drawable.lace4, 50));
        productModels.add(new ProductModel(R.drawable.lace5, 70));
        productModels.add(new ProductModel(R.drawable.lace6, 20));
        productModels.add(new ProductModel(R.drawable.lace7, 80));
        productModels.add(new ProductModel(R.drawable.lace8, 20));
        return productModels;
    }

    private ArrayList<ProductModel> getZippers() {
        ArrayList<ProductModel> productModels = new ArrayList<>();

        productModels.add(new ProductModel(R.drawable.zipper1, 40));
        productModels.add(new ProductModel(R.drawable.zipper2, 20));
        productModels.add(new ProductModel(R.drawable.zipper3, 60));
        productModels.add(new ProductModel(R.drawable.zipper4, 50));
        productModels.add(new ProductModel(R.drawable.zipper5, 70));
        productModels.add(new ProductModel(R.drawable.zipper6, 20));
        productModels.add(new ProductModel(R.drawable.zipper7, 80));
        productModels.add(new ProductModel(R.drawable.zipper8, 20));
        productModels.add(new ProductModel(R.drawable.zipper9, 80));
        productModels.add(new ProductModel(R.drawable.zipper10, 20));

        return productModels;
    }
}
