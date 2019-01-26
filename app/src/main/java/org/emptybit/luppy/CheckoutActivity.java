package org.emptybit.luppy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.emptybit.help.Validate;
import org.emptybit.luppy.Models.CartModel;
import org.emptybit.luppy.Models.OrderModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.emptybit.luppy.ShopActivity.cart;

public class CheckoutActivity extends AppCompatActivity {

    TextInputEditText xName, xEmail, xPhone, xAddress, xComment, xCardNumber, xExpireDate, xCVC;
    TextView xAmount;
    Button xPay;

    FirebaseDatabase database;
    DatabaseReference reference;

    AlertDialog.Builder builder;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("orders");

        builder = new AlertDialog.Builder(this);
        alert = builder.create();

        for (CartModel cartModel : cart) {
            if (cartModel.getQuantity() == 0) cart.remove(cartModel);
        }

        xName = findViewById(R.id.checkout_customer_name);
        xEmail = findViewById(R.id.checkout_customer_email);
        xPhone = findViewById(R.id.checkout_customer_phone);
        xAddress = findViewById(R.id.checkout_customer_address);
        xComment = findViewById(R.id.checkout_customer_comment);
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

                boolean flag = true;

                if (!Validate.Input(xName)) {
                    builder.setMessage("Name is required");
                    alert = builder.create();
                    alert.show();
                    flag = false;
                } else if (!Validate.Input(xEmail)) {
                    builder.setMessage("Email is required");
                    alert = builder.create();
                    alert.show();
                    flag = false;
                } else if (!Validate.Input(xPhone)) {
                    builder.setMessage("Phone is required");
                    alert = builder.create();
                    alert.show();
                    flag = false;
                } else if (!Validate.Input(xAddress)) {
                    builder.setMessage("Address is required");
                    alert = builder.create();
                    alert.show();
                    flag = false;
                } else if (!Validate.Input(xComment)) {
                    builder.setMessage("Comment is required");
                    alert = builder.create();
                    alert.show();
                    flag = false;
                } else if (!Validate.Input(xCardNumber)) {
                    builder.setMessage("Card number is required");
                    alert = builder.create();
                    alert.show();
                    flag = false;
                } else if (Validate.Input(xCardNumber)) {
                    if (!xCardNumber.getText().toString().startsWith("3") && !xCardNumber.getText().toString().startsWith("4") && !xCardNumber.getText().toString().startsWith("5")) {
                        builder.setMessage("We only accept American Express, VISA or Master Card for now.\nSorry for the inconvenience.");
                        alert = builder.create();
                        alert.show();
                        flag = false;
                    }
                } else if (!Validate.Input(xExpireDate)) {
                    builder.setMessage("Card expire date is required");
                    alert = builder.create();
                    alert.show();
                    flag = false;
                } else if (Validate.Input(xExpireDate)) {
                    if (xCardNumber.getText().toString().indexOf("/") != 2) {
                        builder.setMessage("Invalid format for expire date.\nCorrect format(MM/YY).");
                        alert.show();
                        alert = builder.create();
                        flag = false;
                    }
                } else if (!Validate.Input(xCVC)) {
                    builder.setMessage("CVC is required");
                    alert = builder.create();
                    alert.show();
                    flag = false;
                }

                if (flag) {
                    String id = reference.push().getKey();
                    Date today = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    OrderModel order = new OrderModel(id, xName.getText().toString(), xEmail.getText().toString(),
                            xPhone.getText().toString(), xAddress.getText().toString(), xComment.getText().toString(),
                            xCardNumber.getText().toString(), xExpireDate.getText().toString(), xCVC.getText().toString(), format.format(today), cart);
                    reference.child(id).setValue(order);
                    cart = new ArrayList<>();
                    builder.setMessage("Order confirmed.\nThanks for shopping with us.");

                    alert = builder.create();
                    alert.show();
                    // Hide after some seconds
                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (alert.isShowing()) {
                                alert.dismiss();
                                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
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
}
