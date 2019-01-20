package org.emptybit.luppy.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.emptybit.luppy.Models.ProductModel;
import org.emptybit.luppy.R;

import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<ProductModel> arrayList;

    public CartAdapter(ArrayList<ProductModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_individual_product_row_layout, viewGroup, false);
        CartAdapter.ViewHolder holder = new CartAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        if (arrayList == null) return 0;
        else return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
