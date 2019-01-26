package org.emptybit.luppy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.emptybit.help.Validate;
import org.emptybit.luppy.Models.ProductModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {

    ImageView xImage;
    TextInputEditText xName, xPrice;
    Spinner xCategory, xSubCategory;
    Button xSubmit;
    byte[] bytes;
    AlertDialog.Builder builder;
    AlertDialog alert;

    Uri imageUri;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Toolbar toolbar = findViewById(R.id.add_product_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        xImage = findViewById(R.id.add_product_image);
        xName = findViewById(R.id.add_product_name);
        xPrice = findViewById(R.id.add_product_price);
        xCategory = findViewById(R.id.add_product_category);
        xSubCategory = findViewById(R.id.add_product_sub_category);
        xSubmit = findViewById(R.id.add_product_submit);

        builder = new AlertDialog.Builder(this);
        alert = builder.create();

        ArrayList<String> categoryArray = new ArrayList();
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

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("products");

        xCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
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

        xSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;

                if (!Validate.Input(xName)) {
                    builder.setMessage("Name is required");
                    alert.show();
                    flag = false;
                } else if (!Validate.Spinner(xCategory)) {
                    builder.setMessage("Category is required");
                    alert.show();
                    flag = false;
                } else if (!Validate.Spinner(xSubCategory)) {
                    builder.setMessage("Sub category is required");
                    alert.show();
                    flag = false;
                } else if (!Validate.Input(xPrice)) {
                    builder.setMessage("Price is required");
                    alert.show();
                    flag = false;
                } else if (bytes == null || bytes.length == 0) {
                    builder.setMessage("Image is required");
                    alert.show();
                    flag = false;
                }

                if (flag) {
                    String id = reference.push().getKey();
                    String image = Base64.encodeToString(bytes, Base64.NO_WRAP);
                    String name = xName.getText().toString();
                    int price = Integer.parseInt(xPrice.getText().toString());
                    String category = xCategory.getSelectedItem().toString();
                    String sub_category = xSubCategory.getSelectedItem().toString();

                    ProductModel product = new ProductModel(id, name, category, sub_category, price, image);
                    reference.child(id).setValue(product);
                    builder.setMessage("Product created successfully.");
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
}
