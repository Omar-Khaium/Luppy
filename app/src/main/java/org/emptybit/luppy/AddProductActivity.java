package org.emptybit.luppy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class AddProductActivity extends AppCompatActivity {

    ImageView xImage;
    TextInputEditText xName, xPrice;
    Spinner xCategory, xSubCategory;
    Button xSubmit;
    byte[] bytes;

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xCategory.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("products");

        xCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
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
                String id = reference.push().getKey();
                String image = Base64.encodeToString(bytes, Base64.NO_WRAP);
                String name = xName.getText().toString();
                int price = Integer.parseInt(xPrice.getText().toString());
                String category = xCategory.getSelectedItem().toString();
                String sub_category = xSubCategory.getSelectedItem().toString();

                ProductModel product = new ProductModel(id, name, category, sub_category, price, image);
                reference.child(id).setValue(product);
                Snackbar.make(view, "Product added successfully", Snackbar.LENGTH_INDEFINITE).show();

                startActivity(new Intent(getApplicationContext(), AdminDashboardActivity.class));
            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK && requestCode == 100) {

                imageUri = data.getData();
                xImage.setImageURI(imageUri);

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArray);
                bytes = byteArray.toByteArray();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
