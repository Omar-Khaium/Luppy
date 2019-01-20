package org.emptybit.luppy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
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

import org.emptybit.luppy.Models.ProductModel;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

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

    ProductModel product = new ProductModel();

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

        xSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bytes != null) {
                    image = Base64.encodeToString(bytes, Base64.NO_WRAP);
                }
                ProductModel updatedProduct = new ProductModel(product.getId(), xName.getText().toString(),
                        xCategory.getSelectedItem().toString(), xSubCategory.getSelectedItem().toString(),
                        Integer.parseInt(xPrice.getText().toString()), image);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference reference = database.getReference("products").child(product.getId());
                reference.setValue(updatedProduct);
                Toast.makeText(getApplicationContext(), "Profile updated Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditProductActivity.this, AdminDashboardActivity.class));
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xCategory.setAdapter(adapter);

        xCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isFirstTime) {
                    isFirstTime = false;
                } else {
                    if (i != 0) {
                        switch (i) {
                            case 1:
                                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.ladies_category_array, android.R.layout.simple_spinner_item);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                xSubCategory.setAdapter(adapter);
                                break;

                            case 2:
                                adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.gents_category_array, android.R.layout.simple_spinner_item);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                xSubCategory.setAdapter(adapter);
                                break;

                            case 3:
                                adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.extra_category_array, android.R.layout.simple_spinner_item);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                xSubCategory.setAdapter(adapter);
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
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
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

                        String categoryArr[] = getResources().getStringArray(R.array.category_array);
                        xCategory.setSelection(Arrays.asList(categoryArr).indexOf(product.getCategory()));

                        ArrayAdapter<CharSequence> adapter = null;
                        String subCategoryArr[] = getResources().getStringArray(R.array.ladies_category_array);

                        switch (xCategory.getSelectedItemPosition()) {
                            case 1:
                                subCategoryArr = getResources().getStringArray(R.array.ladies_category_array);
                                adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.ladies_category_array, android.R.layout.simple_spinner_item);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                xSubCategory.setAdapter(adapter);
                                switch (Arrays.asList(subCategoryArr).indexOf(product.getSub_category())) {
                                    case 1:
                                        xSubCategory.setSelection(1);
                                        break;
                                    case 2:
                                        xSubCategory.setSelection(2);
                                        break;
                                    case 3:
                                        xSubCategory.setSelection(3);
                                        break;
                                }
                                break;

                            case 2:
                                subCategoryArr = getResources().getStringArray(R.array.gents_category_array);
                                adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.gents_category_array, android.R.layout.simple_spinner_item);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                xSubCategory.setAdapter(adapter);
                                switch (Arrays.asList(subCategoryArr).indexOf(product.getSub_category())) {
                                    case 1:
                                        xSubCategory.setSelection(1);
                                        break;
                                    case 2:
                                        xSubCategory.setSelection(2);
                                        break;
                                    case 3:
                                        xSubCategory.setSelection(3);
                                        break;
                                }
                                break;

                            case 3:
                                subCategoryArr = getResources().getStringArray(R.array.extra_category_array);
                                adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.extra_category_array, android.R.layout.simple_spinner_item);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                xSubCategory.setAdapter(adapter);
                                switch (Arrays.asList(subCategoryArr).indexOf(product.getSub_category())) {
                                    case 1:
                                        xSubCategory.setSelection(1);
                                        break;
                                    case 2:
                                        xSubCategory.setSelection(2);
                                        break;
                                    case 3:
                                        xSubCategory.setSelection(3);
                                        break;
                                    case 4:
                                        xSubCategory.setSelection(4);
                                        break;
                                    case 5:
                                        xSubCategory.setSelection(5);
                                        break;
                                }
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
