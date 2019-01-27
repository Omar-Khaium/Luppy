package org.emptybit.luppy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.emptybit.help.Format;
import org.emptybit.luppy.Models.CartModel;
import org.emptybit.luppy.Models.OrderModel;
import org.emptybit.luppy.R;

import java.util.ArrayList;

public class OrderMainAdapter extends RecyclerView.Adapter<OrderMainAdapter.ViewHolder> {

    Context context;
    ArrayList<OrderModel> arrayList;

    public OrderMainAdapter(Context context, ArrayList<OrderModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public OrderMainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_row_layout, viewGroup, false);
        OrderMainAdapter.ViewHolder holder = new OrderMainAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderMainAdapter.ViewHolder holder, int position) {
        holder.xDate.setText(Format.Text(arrayList.get(position).getOrderDate()));
        holder.xEmail.setText(Format.Text(arrayList.get(position).getEmail()));
        holder.xComment.setText(Format.Text(arrayList.get(position).getComment()));
        holder.xTotalPrice.setText(Format.Text(calculatePrice(arrayList.get(position).getCartModels())));

        holder.xProductListView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        OrderSecondaryAdapter adapter = new OrderSecondaryAdapter(context, arrayList.get(position).getCartModels());
        holder.xProductListView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private String calculatePrice(ArrayList<CartModel> cartModels) {
        int price = 0;

        for (CartModel cartModel : cartModels) {
            price += (cartModel.getQuantity() * cartModel.getProduct().getPrice());
        }
        return String.valueOf(price);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView xDate, xEmail, xComment, xTotalPrice;
        RecyclerView xProductListView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xDate = itemView.findViewById(R.id.order_date);
            xEmail = itemView.findViewById(R.id.order_email);
            xComment = itemView.findViewById(R.id.order_comment);
            xTotalPrice = itemView.findViewById(R.id.order_total_price);
            xProductListView = itemView.findViewById(R.id.order_list);
        }
    }
}
