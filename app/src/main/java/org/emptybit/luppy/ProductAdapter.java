package org.emptybit.luppy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<ProductModel> arrayList;

    public ProductAdapter(ArrayList<ProductModel> arrayList) {
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
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        holder.xImage.setImageResource(arrayList.get(position).getPath());
        holder.xPrice.setText("Price : " + String.valueOf(arrayList.get(position).getPrice()) + " TK");
    }

    @Override
    public int getItemCount() {
        if (arrayList == null) return 0;
        else return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView xImage;
        TextView xPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xImage = itemView.findViewById(R.id.shop_individual_product_item_image);
            xPrice = itemView.findViewById(R.id.shop_individual_product_item_price);
        }
    }
}
