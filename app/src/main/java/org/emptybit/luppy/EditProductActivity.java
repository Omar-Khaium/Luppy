package org.emptybit.luppy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.emptybit.help.Validate;
import org.emptybit.luppy.Models.ProductModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static org.emptybit.luppy.MainActivity.PRODUCT_ID;

public class EditProductActivity extends AppCompatActivity {

    ImageView xImage;
    TextInputEditText xName, xPrice;
    Spinner xCategory, xSubCategory;
    Button xSubmit;
    boolean isFirstTime = true;
    byte[] bytes;
    String image;
    Uri imageUri;

    AlertDialog.Builder builder;
    AlertDialog alert;

    ProductModel product = new ProductModel();

    ArrayList<String> categoryArray = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        Toolbar toolbar = findViewById(R.id.edit_product_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        xImage = findViewById(R.id.edit_product_image);
        xName = findViewById(R.id.edit_product_name);
        xPrice = findViewById(R.id.edit_product_price);
        xCategory = findViewById(R.id.edit_product_category);
        xSubCategory = findViewById(R.id.edit_product_sub_category);
        xSubmit = findViewById(R.id.edit_product_submit);

        builder = new AlertDialog.Builder(this);
        alert = builder.create();

        xSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;

                if (bytes != null) {
                    image = Base64.encodeToString(bytes, Base64.NO_WRAP);
                }

                if (!Validate.Input(xName)) {
                    builder.setMessage("Name is required");
                    builder.show();
                    flag = false;
                } else if (!Validate.Spinner(xCategory)) {
                    builder.setMessage("Category is required");
                    builder.show();
                    flag = false;
                } else if (!Validate.Spinner(xSubCategory)) {
                    builder.setMessage("Sub category is required");
                    builder.show();
                    flag = false;
                } else if (!Validate.Input(xPrice)) {
                    builder.setMessage("Price is required");
                    builder.show();
                    flag = false;
                } else if (image.isEmpty()) {
                    builder.setMessage("Image is required");
                    builder.show();
                    flag = false;
                }

                if (flag) {
                    ProductModel updatedProduct = new ProductModel(product.getId(), xName.getText().toString(),
                            xCategory.getSelectedItem().toString(), xSubCategory.getSelectedItem().toString(),
                            Integer.parseInt(xPrice.getText().toString()), image);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference reference = database.getReference("products").child(product.getId());
                    reference.setValue(updatedProduct);
                    builder.setMessage("Product updated successfully.");
                    alert = builder.create();
                    alert.show();
                    // Hide after some seconds
                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (alert.isShowing()) {
                                alert.dismiss();
                                startActivity(new Intent(getApplicationContext(), AdminDashboardActivity.class));
                            }
                        }
                    };

                    alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            handler.removeCallbacks(runnable);
                        }
                    });

                    handler.postDelayed(runnable, 1500);
                }
            }
        });
        categoryArray.add("Please select a category");
        categoryArray.add("Ladies Items");
        categoryArray.add("Gents Items");
        categoryArray.add("Extra");
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.drop_down_admin_layout,
                categoryArray
        );
        xCategory.setAdapter(categoryAdapter);

        xCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isFirstTime) {
                    isFirstTime = false;
                } else {
                    if (i != 0) {
                        switch (i) {
                            case 1:
                                ArrayList<String> sub_categoryArray = new ArrayList();
                                sub_categoryArray.add("Please select a sub-category");
                                sub_categoryArray.add("Women's Dress");
                                sub_categoryArray.add("Women's Jeans");
                                sub_categoryArray.add("Women's T-Shirt");
                                ArrayAdapter<String> subCategoryAdapter = new ArrayAdapter<String>(
                                        getApplicationContext(),
                                        R.layout.drop_down_admin_layout,
                                        sub_categoryArray
                                );
                                xSubCategory.setAdapter(subCategoryAdapter);
                                break;

                            case 2:
                                sub_categoryArray = new ArrayList();
                                sub_categoryArray.add("Please select a sub-category");
                                sub_categoryArray.add("Men's Dress");
                                sub_categoryArray.add("Men's Shirt");
                                sub_categoryArray.add("Men's T-Shirt");
                                subCategoryAdapter = new ArrayAdapter<String>(
                                        getApplicationContext(),
                                        R.layout.drop_down_admin_layout,
                                        sub_categoryArray
                                );
                                xSubCategory.setAdapter(subCategoryAdapter);
                                break;

                            case 3:
                                sub_categoryArray = new ArrayList();
                                sub_categoryArray.add("Please select a sub-category");
                                sub_categoryArray.add("Button");
                                sub_categoryArray.add("Embroidery");
                                sub_categoryArray.add("Lace");
                                sub_categoryArray.add("Lace Belts");
                                sub_categoryArray.add("Zipper");
                                subCategoryAdapter = new ArrayAdapter<String>(
                                        getApplicationContext(),
                                        R.layout.drop_down_admin_layout,
                                        sub_categoryArray
                                );
                                xSubCategory.setAdapter(subCategoryAdapter);
                                break;
                        }


                        xSubCategory.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        xImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        getData();

    }

    private void openGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK && requestCode == 1) {

                imageUri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                xImage.setImageBitmap(bitmap);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 25, byteArray);
                bytes = byteArray.toByteArray();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void getData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue(ProductModel.class).getId().equals(PRODUCT_ID)) {
                        product = snapshot.getValue(ProductModel.class);
                        xName.setText(product.getName());
                        xPrice.setText("" + product.getPrice());

                        xCategory.setSelection(categoryArray.indexOf(product.getCategory()));

                        switch (xCategory.getSelectedItemPosition()) {
                            case 1:
                                ArrayList<String> sub_categoryArray = new ArrayList();
                                sub_categoryArray.add("Please select a sub-category");
                                sub_categoryArray.add("Women's Dress");
                                sub_categoryArray.add("Women's Jeans");
                                sub_categoryArray.add("Women's T-Shirt");
                                ArrayAdapter<String> subCategoryAdapter = new ArrayAdapter<String>(
                                        getApplicationContext(),
                                        R.layout.drop_down_admin_layout,
                                        sub_categoryArray
                                );
                                xSubCategory.setAdapter(subCategoryAdapter);
                                xSubCategory.setSelection(sub_categoryArray.indexOf(product.getSub_category()));
                                break;

                            case 2:
                                sub_categoryArray = new ArrayList();
                                sub_categoryArray.add("Please select a sub-category");
                                sub_categoryArray.add("Men's Dress");
                                sub_categoryArray.add("Men's Shirt");
                                sub_categoryArray.add("Men's T-Shirt");
                                subCategoryAdapter = new ArrayAdapter<String>(
                                        getApplicationContext(),
                                        R.layout.drop_down_admin_layout,
                                        sub_categoryArray
                                );
                                xSubCategory.setAdapter(subCategoryAdapter);
                                xSubCategory.setSelection(sub_categoryArray.indexOf(product.getSub_category()));
                                break;

                            case 3:
                                sub_categoryArray = new ArrayList();
                                sub_categoryArray.add("Please select a sub-category");
                                sub_categoryArray.add("Button");
                                sub_categoryArray.add("Embroidery");
                                sub_categoryArray.add("Lace");
                                sub_categoryArray.add("Lace Belts");
                                sub_categoryArray.add("Zipper");
                                subCategoryAdapter = new ArrayAdapter<String>(
                                        getApplicationContext(),
                                        R.layout.drop_down_admin_layout,
                                        sub_categoryArray
                                );
                                xSubCategory.setAdapter(subCategoryAdapter);
                                xSubCategory.setSelection(sub_categoryArray.indexOf(product.getSub_category()));
                                break;
                        }

                        if (product.getPhoto() != null || !product.getPhoto().equals("")) {
                            image = product.getPhoto();
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = 4;
                            byte[] decodedString = Base64.decode(product.getPhoto(), Base64.DEFAULT);
                            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, options);
                            xImage.setImageBitmap(bitmap);
                        } else {
                            xImage.setImageResource(R.drawable.ic_product);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
