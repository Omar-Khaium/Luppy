package org.emptybit.luppy;

import android.content.Context;
import android.content.Intent;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WomenFragmentAdapter extends RecyclerView.Adapter<WomenFragmentAdapter.ViewHolder> {

    Context context;
    ArrayList<ProductModel> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("products");


    public WomenFragmentAdapter(Context context, ArrayList<ProductModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public WomenFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_row_layout, viewGroup, false);
        WomenFragmentAdapter.ViewHolder holder = new WomenFragmentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(WomenFragmentAdapter.ViewHolder holder, final int position) {
        holder.xName.setText(arrayList.get(position).getName());
        holder.xCategory.setText(arrayList.get(position).getCategory());
        holder.xPrice.setText(String.valueOf(arrayList.get(position).getPrice()));
        if (arrayList.get(position).getPhoto() != null && !arrayList.get(position).getPhoto().equals("")) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            byte[] decodedString = Base64.decode(arrayList.get(position).getPhoto(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, options);
            holder.xImage.setImageBitmap(bitmap);
        } else {
            holder.xImage.setImageResource(R.drawable.ic_product);
        }

        holder.xDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child(arrayList.get(position).getId()).removeValue();
                context.startActivity(new Intent(context, AdminDashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView xName, xCategory, xPrice;
        ImageView xImage, xDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xImage = itemView.findViewById(R.id.fragment_row_layout_image);
            xName = itemView.findViewById(R.id.fragment_row_layout_name);
            xCategory = itemView.findViewById(R.id.fragment_row_layout_category);
            xPrice = itemView.findViewById(R.id.fragment_row_layout_price);
            xDelete = itemView.findViewById(R.id.fragment_row_layout_delete);
        }
    }
}
