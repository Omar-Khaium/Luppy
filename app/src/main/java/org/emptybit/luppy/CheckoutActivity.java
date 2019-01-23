package org.emptybit.luppy;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.emptybit.luppy.Models.CartModel;

import static org.emptybit.luppy.ShopActivity.cart;

public class CheckoutActivity extends AppCompatActivity {

    TextInputEditText xCardNumber, xExpireDate, xCVC;
    TextView xAmount;
    Button xPay;

    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("orders");

        xCardNumber = findViewById(R.id.checkout_card_number);
        xExpireDate = findViewById(R.id.checkout_card_expire_date);
        xCVC = findViewById(R.id.checkout_card_cvc);
        xPay = findViewById(R.id.checkout_submit);

        xAmount = findViewById(R.id.checkout_amount);

        xAmount.setText(getIntent().getStringExtra("Amount") + " BDT");

        xCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (xCardNumber.getText().toString().startsWith("3")) {
                    xCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_amex, 0);
                } else if (xCardNumber.getText().toString().startsWith("4")) {
                    xCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visa, 0);
                } else if (xCardNumber.getText().toString().startsWith("5")) {
                    xCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_mastercard, 0);
                } else {
                    xCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (xCardNumber.getText().toString().startsWith("3")) {
                    xCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_amex, 0);
                } else if (xCardNumber.getText().toString().startsWith("4")) {
                    xCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visa, 0);
                } else if (xCardNumber.getText().toString().startsWith("5")) {
                    xCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_mastercard, 0);
                } else {
                    xCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }
        });

        xPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CartModel product : cart) {
                    String id = reference.push().getKey();
                    product.setId(id);
                    product.setCardNumber(xCardNumber.getText().toString());
                    product.setCardCVC(xCVC.getText().toString());
                    product.setCardExpireDate(xExpireDate.getText().toString());
                    reference.child(id).setValue(product);

                }
                Snackbar.make(view, "Shopping Completed", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
