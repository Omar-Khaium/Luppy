package org.emptybit.luppy.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.emptybit.luppy.Adapters.ExtraFragmentAdapter;
import org.emptybit.luppy.AddProductActivity;
import org.emptybit.luppy.Models.ProductModel;
import org.emptybit.luppy.R;

import java.util.ArrayList;

public class AdminExtrasItemsFragment extends Fragment {

    View view;
    FirebaseDatabase database;
    private RecyclerView xListView;
    private ArrayList<ProductModel> arrayList;
    private FloatingActionButton xAdd;
    private LinearLayout xShimmerLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_extra_items, container, false);
        xListView = view.findViewById(R.id.extra_recycler_list);
        xAdd = view.findViewById(R.id.extra_fragment_add);
        xShimmerLayout = view.findViewById(R.id.fragment_shimmer_layout);

        getData();

        xAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddProductActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrayList = new ArrayList<>();
    }


    public int getData() {
        try {
            arrayList = new ArrayList<>();
            database = FirebaseDatabase.getInstance();
            database.getReference("products").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        try {
                            if (snapshot.getValue(ProductModel.class).getCategory().equals("Extra")) {
                                arrayList.add(snapshot.getValue(ProductModel.class));
                            }
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    ExtraFragmentAdapter adapter = new ExtraFragmentAdapter(getContext(), arrayList);
                    xListView.setAdapter(adapter);
                    xListView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    xListView.setVisibility(View.VISIBLE);
                    xShimmerLayout.setVisibility(View.GONE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), "Database Exception : " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            return 1;
        } catch (Exception e) {
            Toast.makeText(getContext(), "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return 0;
        }
    }
}
