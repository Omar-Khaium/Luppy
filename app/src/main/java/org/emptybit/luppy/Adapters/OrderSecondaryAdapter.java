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
import android.widget.ImageView;
import android.widget.TextView;

import org.emptybit.help.Format;
import org.emptybit.luppy.Models.CartModel;
import org.emptybit.luppy.R;

import java.util.ArrayList;

public class OrderSecondaryAdapter extends RecyclerView.Adapter<OrderSecondaryAdapter.ViewHolder> {

    Context context;
    ArrayList<CartModel> arrayList;

    public OrderSecondaryAdapter(Context context, ArrayList<CartModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public OrderSecondaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_individual_item_layout, viewGroup, false);
        OrderSecondaryAdapter.ViewHolder holder = new OrderSecondaryAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final OrderSecondaryAdapter.ViewHolder holder, final int position) {

        ArrayList<String> sizeArray = new ArrayList();
        sizeArray.add("Select size");
        sizeArray.add("Size : S");
        sizeArray.add("Size : M");
        sizeArray.add("Size : L");
        sizeArray.add("Size : XL");
        sizeArray.add("Size : XXL");
        holder.xName.setText(Format.Text(arrayList.get(position).getProduct().getName()));
        holder.xCategory.setText(Format.Text(arrayList.get(position).getProduct().getSub_category()));
        holder.xSize.setText(sizeArray.get(arrayList.get(position).getSize()));
        holder.xQuantity.setText(String.valueOf(arrayList.get(position).getQuantity()) + " items(s)");
        if (arrayList.get(position).getProduct().getPhoto() != null && !arrayList.get(position).getProduct().getPhoto().equals("")) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            byte[] decodedString = Base64.decode(arrayList.get(position).getProduct().getPhoto(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, options);
            holder.xImage.setImageBitmap(bitmap);
        } else {
            holder.xImage.setImageResource(R.drawable.ic_product);
        }
    }

    @Override
    public int getItemCount() {
        if (arrayList == null) return 0;
        else return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView xImage;
        TextView xName, xCategory, xSize, xQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xImage = itemView.findViewById(R.id.order_product_image);
            xName = itemView.findViewById(R.id.order_product_name);
            xCategory = itemView.findViewById(R.id.order_product_category);
            xSize = itemView.findViewById(R.id.order_product_size);
            xQuantity = itemView.findViewById(R.id.order_product_quantity);
        }
    }
}
