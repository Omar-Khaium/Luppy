package org.emptybit.luppy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    Context context;
    ArrayList<CategoryModel> arrayList;

    public ShopAdapter(Context context, ArrayList<CategoryModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_items_row_layout, viewGroup, false);
        ShopAdapter.ViewHolder holder = new ShopAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShopAdapter.ViewHolder holder, int position) {
        holder.xCategoryName.setText(arrayList.get(position).getName());

        holder.xProductListView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        ProductAdapter adapter = new ProductAdapter(arrayList.get(position).getProductModels());
        holder.xProductListView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView xCategoryName;
        RecyclerView xProductListView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xCategoryName = itemView.findViewById(R.id.shop_items_row_list_category);
            xProductListView = itemView.findViewById(R.id.shop_items_row_list);
        }
    }
}
