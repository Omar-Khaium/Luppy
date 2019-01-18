package org.emptybit.luppy;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<OrderModel> cart = new ArrayList<>();
    Button xSubmit;
    TextInputEditText xEmail, xPassword;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
    };

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        xSubmit = findViewById(R.id.login_submit);
        xEmail = findViewById(R.id.login_email);
        xPassword = findViewById(R.id.login_password);

        xSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xEmail.getText().toString().equals("admin")) {
                    startActivity(new Intent(MainActivity.this, AdminDashboardActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                }
            }
        });
    }
}
