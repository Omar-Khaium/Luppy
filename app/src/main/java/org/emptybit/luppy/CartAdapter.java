package org.emptybit.luppy;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static org.emptybit.luppy.MainActivity.cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_row_layout, viewGroup, false);
        CartAdapter.ViewHolder holder = new CartAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, final int position) {
        holder.xImage.setImageResource(cart.get(position).getProductModel().getPath());
        holder.xName.setText("Item " + String.valueOf(position + 1));
        holder.xPrice.setText(cart.get(position).getProductModel().getPrice() + " TK");
        holder.xCount.setText(String.valueOf(cart.get(position).getQuantity()));
        holder.xTotal.setText(String.valueOf(cart.get(position).getQuantity() * cart.get(position).getProductModel().getPrice()) + " TK");
        holder.xAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.xRemove.isEnabled()) holder.xRemove.setEnabled(true);
                holder.xCount.setText(String.valueOf(Integer.parseInt(holder.xCount.getText().toString()) + 1));
                cart.get(position).setQuantity(cart.get(position).getQuantity() + 1);
                holder.xTotal.setText(String.valueOf(cart.get(position).getQuantity() * cart.get(position).getProductModel().getPrice()) + " TK");
                Snackbar.make(view, cart.get(position).getQuantity() + " items available at the cart", Snackbar.LENGTH_SHORT).show();
            }
        });

        holder.xRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(holder.xCount.getText().toString()) >= 1) {
                    holder.xCount.setText(String.valueOf(Integer.parseInt(holder.xCount.getText().toString()) - 1));
                    cart.get(position).setQuantity(cart.get(position).getQuantity() - 1);
                    holder.xTotal.setText(String.valueOf(cart.get(position).getQuantity() * cart.get(position).getProductModel().getPrice()) + " TK");
                    Snackbar.make(view, cart.get(position).getQuantity() + " items left to the cart", Snackbar.LENGTH_SHORT).show();
                } else {
                    holder.xRemove.setEnabled(false);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return cart.size();
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
        EditText xCount;
        TextView xName, xPrice, xTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            xImage = itemView.findViewById(R.id.cart_item_image);
            xAdd = itemView.findViewById(R.id.cart_item_add);
            xRemove = itemView.findViewById(R.id.cart_item_remove);
            xCount = itemView.findViewById(R.id.cart_item_count);
            xName = itemView.findViewById(R.id.cart_item_name);
            xPrice = itemView.findViewById(R.id.cart_item_price);
            xTotal = itemView.findViewById(R.id.cart_item_total);

        }
    }
}

