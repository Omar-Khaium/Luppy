package org.emptybit.luppy;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static org.emptybit.luppy.MainActivity.cart;

public class ShopActivity extends AppCompatActivity {

    private RecyclerView xListView;
    private ShopAdapter adapter;
    private ArrayList<CategoryModel> categoryModels;
    private FloatingActionButton xCart;
    private AlertDialog.Builder alertBuilder;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Toolbar toolbar = findViewById(R.id.shop_items_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        xListView = findViewById(R.id.shop_items_to_display_list);
        xListView.setLayoutManager(new LinearLayoutManager(this));
        xCart = findViewById(R.id.shop_items_to_display_cart);

        alertBuilder = new AlertDialog.Builder(this);
        alertDialog = alertBuilder.create();

        xCart.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                final View convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.cart_layout, new LinearLayout(getApplicationContext()));
                alertDialog.setView(convertView);
                alertDialog.create();
                alertDialog.show();
                alertBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        ((ViewGroup) convertView.getParent()).removeView(convertView);
                    }
                });

                final ImageView xClose = convertView.findViewById(R.id.cart_layout_close);
                TextView xTotalAmount = convertView.findViewById(R.id.cart_layout_total);
                RecyclerView recyclerView = convertView.findViewById(R.id.cart_layout_list);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new CartAdapter());

                int price = 0;
                for (OrderModel order : cart) {
                    price += order.getQuantity() * order.getProductModel().getPrice();
                }
                xTotalAmount.setText(String.valueOf(price) + " TK");

                xClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });

        switch (DashboardActivity.SELECTED_ITEM) {
            case "Extra":
                adapter = new ShopAdapter(getApplicationContext(), getExtras());
                break;
        }

        xListView.setAdapter(adapter);
    }

    private ArrayList<CategoryModel> getExtras() {
        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
//        categoryModels.add(new CategoryModel("Buttons", getButtons()));
//        categoryModels.add(new CategoryModel("Embroidery", getEmbroidery()));
//        categoryModels.add(new CategoryModel("Lace Belts", getLaceBelts()));
//        categoryModels.add(new CategoryModel("Lace", getLaces()));
//        categoryModels.add(new CategoryModel("Zippers", getZippers()));
        return categoryModels;
    }

//    private ArrayList<ProductModel> getButtons() {
//        ArrayList<ProductModel> productModels = new ArrayList<>();
//        productModels.add(new ProductModel(1001, R.drawable.button1, 20));
//        productModels.add(new ProductModel(1002, R.drawable.button2, 20));
//        productModels.add(new ProductModel(1003, R.drawable.button3, 20));
//        productModels.add(new ProductModel(1004, R.drawable.button4, 20));
//        productModels.add(new ProductModel(1005, R.drawable.button5, 20));
//        productModels.add(new ProductModel(1006,R.drawable.button6, 20));
//        productModels.add(new ProductModel(1007,R.drawable.button7, 20));
//        productModels.add(new ProductModel(1008,R.drawable.button8, 20));
//        productModels.add(new ProductModel(1009,R.drawable.button9, 20));
//        productModels.add(new ProductModel(1010,R.drawable.button10, 20));
//        productModels.add(new ProductModel(1011,R.drawable.button11, 20));
//        productModels.add(new ProductModel(1012,R.drawable.button12, 20));
//        productModels.add(new ProductModel(1013,R.drawable.button13, 20));
//        productModels.add(new ProductModel(1014,R.drawable.button14, 20));
//        productModels.add(new ProductModel(1015,R.drawable.button15, 20));
//        productModels.add(new ProductModel(1016,R.drawable.button16, 20));
//        productModels.add(new ProductModel(1017,R.drawable.button17, 20));
//        productModels.add(new ProductModel(1018,R.drawable.button18, 20));
//        productModels.add(new ProductModel(1019,R.drawable.button19, 20));
//        productModels.add(new ProductModel(1021,R.drawable.button21, 20));
//        productModels.add(new ProductModel(1022,R.drawable.button22, 20));
//        productModels.add(new ProductModel(1023,R.drawable.button23, 20));
//        return productModels;
//    }
//
//    private ArrayList<ProductModel> getEmbroidery() {
//        ArrayList<ProductModel> productModels = new ArrayList<>();
//        productModels.add(new ProductModel(2001, R.drawable.embroidery1, 25));
//        productModels.add(new ProductModel(2002, R.drawable.embroidery2, 25));
//        productModels.add(new ProductModel(2003, R.drawable.embroidery3, 25));
//        productModels.add(new ProductModel(2004, R.drawable.embroidery4, 25));
//        productModels.add(new ProductModel(2005, R.drawable.embroidery5, 25));
////        productModels.add(new ProductModel(2006,R.drawable.embroidery6, 20));
////        productModels.add(new ProductModel(2007,R.drawable.embroidery7, 80));
////        productModels.add(new ProductModel(2008,R.drawable.embroidery8, 20));
////        productModels.add(new ProductModel(2009,R.drawable.embroidery9, 80));
////        productModels.add(new ProductModel(2010,R.drawable.embroidery10, 20));
////        productModels.add(new ProductModel(2011,R.drawable.embroidery11, 70));
////        productModels.add(new ProductModel(2012,R.drawable.embroidery12, 20));
////        productModels.add(new ProductModel(2013,R.drawable.embroidery13, 50));
////        productModels.add(new ProductModel(2015,R.drawable.embroidery15, 80));
////        productModels.add(new ProductModel(2016,R.drawable.embroidery16, 80));
////        productModels.add(new ProductModel(2017,R.drawable.embroidery17, 20));
////        productModels.add(new ProductModel(2018,R.drawable.embroidery18, 20));
//        return productModels;
//    }
//
//    private ArrayList<ProductModel> getLaceBelts() {
//        ArrayList<ProductModel> productModels = new ArrayList<>();
//        productModels.add(new ProductModel(3001, R.drawable.lace_belts1, 40));
//        productModels.add(new ProductModel(3002, R.drawable.lace_belts2, 40));
//        productModels.add(new ProductModel(3003, R.drawable.lace_belts3, 40));
//        productModels.add(new ProductModel(3004, R.drawable.lace_belts4, 40));
//        productModels.add(new ProductModel(3005, R.drawable.lace_belts5, 40));
////        productModels.add(new ProductModel(3006,R.drawable.lace_belts6, 20));
////        productModels.add(new ProductModel(3007,R.drawable.lace_belts7, 20));
////        productModels.add(new ProductModel(3008,R.drawable.lace_belts8, 20));
////        productModels.add(new ProductModel(3009,R.drawable.lace_belts9, 20));
////        productModels.add(new ProductModel(3010,R.drawable.lace_belts10, 20));
////        productModels.add(new ProductModel(3011,R.drawable.lace_belts11, 20));
////        productModels.add(new ProductModel(3012,R.drawable.lace_belts12, 20));
////        productModels.add(new ProductModel(3013,R.drawable.lace_belts13, 20));
////        productModels.add(new ProductModel(3014,R.drawable.lace_belts14, 20));
////        productModels.add(new ProductModel(3015,R.drawable.lace_belts15, 20));
////        productModels.add(new ProductModel(3016,R.drawable.lace_belts16, 20));
////        productModels.add(new ProductModel(3017,R.drawable.lace_belts17, 20));
////        productModels.add(new ProductModel(3018,R.drawable.lace_belts18, 20));
////        productModels.add(new ProductModel(3019,R.drawable.lace_belts19, 20));
//        return productModels;
//    }
//
//    private ArrayList<ProductModel> getLaces() {
//        ArrayList<ProductModel> productModels = new ArrayList<>();
//        productModels.add(new ProductModel(4001, R.drawable.lace1, 15));
//        productModels.add(new ProductModel(4002, R.drawable.lace2, 15));
//        productModels.add(new ProductModel(4003, R.drawable.lace3, 15));
//        productModels.add(new ProductModel(4004, R.drawable.lace4, 15));
//        productModels.add(new ProductModel(4005, R.drawable.lace5, 15));
////        productModels.add(new ProductModel(4006,R.drawable.lace6, 20));
////        productModels.add(new ProductModel(4007,R.drawable.lace7, 80));
////        productModels.add(new ProductModel(4008,R.drawable.lace8, 20));
//        return productModels;
//    }
//
//    private ArrayList<ProductModel> getZippers() {
//        ArrayList<ProductModel> productModels = new ArrayList<>();
//
//        productModels.add(new ProductModel(5001, R.drawable.zipper1, 35));
//        productModels.add(new ProductModel(5002, R.drawable.zipper2, 35));
//        productModels.add(new ProductModel(5003, R.drawable.zipper3, 35));
//        productModels.add(new ProductModel(5004, R.drawable.zipper4, 35));
//        productModels.add(new ProductModel(5005, R.drawable.zipper5, 35));
////        productModels.add(new ProductModel(5006,R.drawable.zipper6, 20));
////        productModels.add(new ProductModel(5007,R.drawable.zipper7, 80));
////        productModels.add(new ProductModel(5008,R.drawable.zipper8, 20));
////        productModels.add(new ProductModel(5009,R.drawable.zipper9, 80));
////        productModels.add(new ProductModel(5010,R.drawable.zipper10, 20));
//
//        return productModels;
//    }
}
