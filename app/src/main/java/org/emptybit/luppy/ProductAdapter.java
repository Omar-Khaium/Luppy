package org.emptybit.luppy;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static org.emptybit.luppy.MainActivity.cart;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<ProductModel> arrayList;
    private long mLastClickTime = 0;

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
    public void onBindViewHolder(final ProductAdapter.ViewHolder holder, final int position) {
        holder.xImage.setImageResource(arrayList.get(position).getPath());
        holder.xPrice.setText("Price : " + String.valueOf(arrayList.get(position).getPrice()) + " TK");

        holder.xAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.xAddToCart.setVisibility(View.GONE);
                holder.xCartLayout.setVisibility(View.VISIBLE);
                holder.xAdd.setEnabled(true);
                holder.xRemove.setEnabled(true);
                holder.xCount.setText("1");
                if (findOrder(arrayList.get(position).getId()) == null) {
                    cart.add(new OrderModel(arrayList.get(position), 1));
                    Snackbar.make(view, "1 item added at the cart", Snackbar.LENGTH_SHORT).show();

                } else {
                    OrderModel order = findOrder(arrayList.get(position).getId());
                    order.setQuantity(order.getQuantity() + 1);
                    Snackbar.make(view, order.getQuantity() + " items available at the cart", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        holder.xAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.xRemove.isEnabled()) holder.xRemove.setEnabled(true);
                holder.xCount.setText(String.valueOf(Integer.parseInt(holder.xCount.getText().toString()) + 1));
                OrderModel order = findOrder(arrayList.get(position).getId());
                order.setQuantity(order.getQuantity() + 1);
                Snackbar.make(view, order.getQuantity() + " items available at the cart", Snackbar.LENGTH_SHORT).show();
            }
        });

        holder.xRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(holder.xCount.getText().toString()) > 1) {
                    holder.xCount.setText(String.valueOf(Integer.parseInt(holder.xCount.getText().toString()) - 1));
                    OrderModel order = findOrder(arrayList.get(position).getId());
                    order.setQuantity(order.getQuantity() - 1);
                    Snackbar.make(view, order.getQuantity() + " items left to the cart", Snackbar.LENGTH_SHORT).show();
                } else {
                    holder.xAddToCart.setVisibility(View.VISIBLE);
                    holder.xCartLayout.setVisibility(View.GONE);
                    holder.xRemove.setEnabled(false);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrayList == null) return 0;
        else return arrayList.size();
    }

    public OrderModel findOrder(int id) {
        for (OrderModel order : cart) {
            if (order.getProductModel().getId() == id) {
                return order;
            }
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView xImage, xAdd, xRemove;
        TextView xPrice;
        EditText xCount;
        Button xAddToCart;
        LinearLayout xCartLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xImage = itemView.findViewById(R.id.shop_individual_product_item_image);
            xPrice = itemView.findViewById(R.id.shop_individual_product_item_price);
            xAdd = itemView.findViewById(R.id.shop_individual_product_item_cart_add);
            xRemove = itemView.findViewById(R.id.shop_individual_product_item_cart_remove);
            xCount = itemView.findViewById(R.id.shop_individual_product_item_cart_count);
            xAddToCart = itemView.findViewById(R.id.shop_individual_product_item_add_to_cart);
            xCartLayout = itemView.findViewById(R.id.shop_individual_product_item_cart_layout);
        }
    }
}
