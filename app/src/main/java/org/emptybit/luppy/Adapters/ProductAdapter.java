package org.emptybit.luppy.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.emptybit.luppy.Models.CartModel;
import org.emptybit.luppy.Models.ProductModel;
import org.emptybit.luppy.R;

import java.util.ArrayList;

import static org.emptybit.luppy.ShopActivity.cart;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    ArrayList<ProductModel> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("carts");

    public ProductAdapter(Context context, ArrayList<ProductModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_individual_product_row_layout, viewGroup, false);
        ProductAdapter.ViewHolder holder = new ProductAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ViewHolder holder, final int position) {
        holder.xCategory.setText(arrayList.get(position).getName());
        holder.xPrice.setText("Price : " + String.valueOf(arrayList.get(position).getPrice()) + " TK");
        if (arrayList.get(position).getPhoto() != null && !arrayList.get(position).getPhoto().equals("")) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            byte[] decodedString = Base64.decode(arrayList.get(position).getPhoto(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, options);
            holder.xImage.setImageBitmap(bitmap);
        } else {
            holder.xImage.setImageResource(R.drawable.ic_product);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.size, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.xSize.setAdapter(adapter);

        holder.xAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.xSize.getSelectedItemPosition() == 0) {
                    Snackbar.make(view, "Please select a valid size", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).setActionTextColor(Color.RED).show();
                } else {
                    holder.xAddToCart.setText("Added to cart");
                    holder.xAddToCart.setEnabled(false);
                    holder.xSize.setEnabled(false);
                    cart.add(new CartModel(arrayList.get(position), holder.xSize.getSelectedItemPosition()));
                    Snackbar.make(view, "", Snackbar.LENGTH_SHORT).setAction("Added", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).setActionTextColor(Color.GREEN).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrayList == null) return 0;
        else return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView xImage;
        TextView xCategory, xPrice;
        Button xAddToCart;
        Spinner xSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xImage = itemView.findViewById(R.id.shop_individual_product_item_image);
            xCategory = itemView.findViewById(R.id.shop_individual_product_item_category);
            xPrice = itemView.findViewById(R.id.shop_individual_product_item_price);
            xSize = itemView.findViewById(R.id.shop_individual_product_item_size);
            xAddToCart = itemView.findViewById(R.id.shop_individual_product_item_add_to_cart);
        }
    }
}
