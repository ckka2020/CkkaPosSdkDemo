package com.ckka.ckka_sdk_integration_sample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
    Activity mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ckkaSdk = new CkkaSdk(this);
        findViewById(R.id.txtSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalAmount = 100.50;
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
        String jsonString = "{\"bill_id\":\"5e4574996d5f4958bef10052\",\"products\":[{\"product_code\":\"01\",\"description\":\"item-1\",\"qty\":5,\"unitRate\":1,\"amount\":5},{\"product_code\":\"02\",\"description\":\"item-2\",\"qty\":5,\"unitRate\":1,\"amount\":5}],\"gross_total\":10,\"sales_tax\":0,\"net_total\":10}";
        return jsonString;
    }

    @Override
    public void onSuccess(String ack_id, String message) {
        Toast.makeText(mContext, "AckId: "+ack_id+ "\nMsg: "+ message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
}
