package com.ckka.ckka_sdk_integration_sample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ckka.ckkapossdk.CkkaSdk;
import com.ckka.ckkapossdk.OnCkkaActionListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements OnCkkaActionListener {
    CkkaSdk ckkaSdk;
    double totalAmount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.txtSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalAmount = 100.50;
                ckkaSdk = CkkaSdk.getInstance(MainActivity.this);
                ckkaSdk.sendDataToCkka(
                        /*<YOUR POS-ID HERE>*/ getPOSid(),
                        /*<YOUR TOTAL AMOUNT HERE>*/ totalAmount,
                        /*<YOUR CART DETAILS HERE>*/ getJsonCartInfo(),
                        MainActivity.this);
            }
        });
    }

    private String getPOSid()
    {
        /*eg. */
//        return "5e731afe6d5f11169bef15e9";
        return "<YOUR POS-ID HERE>";
    }

    /*  Your cart details or whatever info you want to share
        should be in Json format string
     */
    private String getJsonCartInfo()
    {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject("{\"Apple\":\"1kg\",\"Banana\":\"6nos\",\"Orange\":5kg,\n" +
                    "\"Potato\":\"20kg\",\"Mango\":\"5kg\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
