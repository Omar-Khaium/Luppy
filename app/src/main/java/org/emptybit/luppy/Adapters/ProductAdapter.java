package org.emptybit.luppy.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.emptybit.luppy.Models.ProductModel;
import org.emptybit.luppy.R;

import java.util.ArrayList;

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
        holder.xAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.xAddToCart.setVisibility(View.GONE);
                holder.xCartLayout.setVisibility(View.VISIBLE);
                holder.xAdd.setEnabled(true);
                holder.xRemove.setEnabled(true);
                holder.xCount.setText("1");
            }
        });

        holder.xAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        holder.xRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrayList == null) return 0;
        else return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView xImage, xAdd, xRemove;
        TextView xCategory, xPrice;
        EditText xCount;
        Button xAddToCart;
        LinearLayout xCartLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xImage = itemView.findViewById(R.id.shop_individual_product_item_image);
            xCategory = itemView.findViewById(R.id.shop_individual_product_item_category);
            xPrice = itemView.findViewById(R.id.shop_individual_product_item_price);
            xAdd = itemView.findViewById(R.id.shop_individual_product_item_cart_add);
            xRemove = itemView.findViewById(R.id.shop_individual_product_item_cart_remove);
            xCount = itemView.findViewById(R.id.shop_individual_product_item_cart_count);
            xAddToCart = itemView.findViewById(R.id.shop_individual_product_item_add_to_cart);
            xCartLayout = itemView.findViewById(R.id.shop_individual_product_item_cart_layout);
        }
    }
}
