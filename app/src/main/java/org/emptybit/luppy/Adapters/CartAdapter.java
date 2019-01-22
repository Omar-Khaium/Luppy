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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.emptybit.luppy.R;

import static org.emptybit.luppy.CartActivity.xTotalAmount;
import static org.emptybit.luppy.ShopActivity.cart;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;

    public CartAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_row_layout, viewGroup, false);
        CartAdapter.ViewHolder holder = new CartAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, final int position) {
        holder.xName.setText(cart.get(position).getProduct().getName());
        holder.xPrice.setText("" + cart.get(position).getProduct().getPrice());
        if (cart.get(position).getProduct().getPhoto() != null && !cart.get(position).getProduct().getPhoto().equals("")) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            byte[] decodedString = Base64.decode(cart.get(position).getProduct().getPhoto(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, options);
            holder.xImage.setImageBitmap(bitmap);
        } else {
            holder.xImage.setImageResource(R.drawable.ic_product);
        }
        holder.xCount.setText("" + 1);

        holder.xTotal.setText("" + cart.get(position).getProduct().getPrice());


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.size, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.xSize.setAdapter(adapter);

        holder.xSize.setSelection(cart.get(position).getsize());

        xTotalAmount.setText(String.valueOf(Integer.parseInt(xTotalAmount.getText().toString()) + cart.get(position).getProduct().getPrice()));
        holder.xAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.xRemove.setEnabled(true);
                holder.xCount.setText("" + (Integer.parseInt(holder.xCount.getText().toString()) + 1));
                holder.xTotal.setText("" + (Integer.parseInt(holder.xCount.getText().toString()) * cart.get(position).getProduct().getPrice()));
                xTotalAmount.setText(String.valueOf(Integer.parseInt(xTotalAmount.getText().toString()) + cart.get(position).getProduct().getPrice()));
            }
        });

        holder.xRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.xCount.getText().toString().equals("1")) {
                    holder.xRemove.setEnabled(false);
                }
                holder.xCount.setText("" + (Integer.parseInt(holder.xCount.getText().toString()) - 1));
                holder.xTotal.setText("" + (Integer.parseInt(holder.xCount.getText().toString()) * cart.get(position).getProduct().getPrice()));
                xTotalAmount.setText(String.valueOf(Integer.parseInt(xTotalAmount.getText().toString()) - cart.get(position).getProduct().getPrice()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (cart == null) return 0;
        else return cart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView xImage, xAdd, xRemove;
        TextView xName, xPrice, xCount, xTotal;
        Spinner xSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            xAdd = itemView.findViewById(R.id.cart_item_add);
            xRemove = itemView.findViewById(R.id.cart_item_remove);
            xImage = itemView.findViewById(R.id.cart_item_image);
            xName = itemView.findViewById(R.id.cart_item_name);
            xPrice = itemView.findViewById(R.id.cart_item_price);
            xCount = itemView.findViewById(R.id.cart_item_count);
            xTotal = itemView.findViewById(R.id.cart_item_total);
            xSize = itemView.findViewById(R.id.cart_item_size);
        }
    }
}
