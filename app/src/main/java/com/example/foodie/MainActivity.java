package com.example.foodie;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText usernameText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.sendButton);
        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);

        RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "http://10.0.2.2/foodie/add.php";
        String url = BuildConfig.BASE_URL + "/foodie/add.php";

        btn.setOnClickListener(v -> {

            final String name = String.valueOf(usernameText.getText());
            final String password = String.valueOf(passwordText.getText());
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        // Handle response

                        Log.d("Response: ", response);
                    }, error -> {
                // Handle errors
                Log.e("Error", error.toString());
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", name);
                    params.put("password", password);
                    return params;
                }
            };
            queue.add(stringRequest);

            System.out.println("Name: " + name);
            System.out.println("Password: " + password);
            System.out.println("url" + url);
        });

    }
}
