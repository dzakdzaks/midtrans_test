package com.dzaky.midtrans_dzakdzaks;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dzaky.midtrans_dzakdzaks.widgets.WidgetsTextView;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.PaymentMethod;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;

public class ProductOrderActivity extends AppCompatActivity implements TransactionFinishedCallback {

    private String image, name;
    private int price, qty;
    private double rating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_review);

        initMidtrans();
        init();
    }

    private void initMidtrans() {
        SdkUIFlowBuilder.init()
                .setContext(this)
                .setMerchantBaseUrl(BuildConfig.MERCHANT_BASE_URL)
                .setClientKey(BuildConfig.MERCHANT_CLIENT_KEY)
                .setTransactionFinishedCallback(this)
                .enableLog(true)
                .setColorTheme(new CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
                .buildSDK();
    }

    private void init() {
        if (getIntent() != null) {
            image = getIntent().getStringExtra("image");
            name = getIntent().getStringExtra("name");
            price = getIntent().getIntExtra("price", 0);
            qty = getIntent().getIntExtra("qty", 0);
            rating = getIntent().getDoubleExtra("rating", 0);

            Glide.with(this)
                    .load(image)
                    .into(((ImageView) findViewById(R.id.product_image)));

            ((WidgetsTextView) findViewById(R.id.text_amount)).setText("Rp. " + (qty * price));
            ((WidgetsTextView) findViewById(R.id.product_name)).setText(name);
            ((WidgetsTextView) findViewById(R.id.product_qty)).setText(String.valueOf(qty));
            ((WidgetsTextView) findViewById(R.id.product_price_amount)).setText("Rp. " + price);


            ((EditText) findViewById(R.id.edit_customer_name)).setText(CustomerData.NAME);
            ((EditText) findViewById(R.id.edit_customer_email)).setText(CustomerData.EMAIL);
            ((EditText) findViewById(R.id.edit_customer_phone)).setText(CustomerData.PHONE);
            ((WidgetsTextView) findViewById(R.id.delivery_address)).setText(CustomerData.ADDRESS);

            findViewById(R.id.button_primary).setOnClickListener(view -> actionButton(price, qty, name));

        }
    }

    private void actionButton(int price, int qty, String name) {
        MidtransSDK.getInstance()
                .setTransactionRequest(CustomerData.getTransactionRequest(
                        "payment-" + System.currentTimeMillis(),
                        price,
                        qty,
                        name
                ));

        MidtransSDK.getInstance().startPaymentUiFlow(this);
    }

    @Override
    public void onTransactionFinished(TransactionResult result) {
        if (result.getResponse() != null) {
            switch (result.getStatus()) {
                case TransactionResult.STATUS_SUCCESS:
                    Toast.makeText(this, "Transaction Success ID : " + result.getResponse().getTransactionId(), Toast.LENGTH_SHORT).show();
                    break;
                case TransactionResult.STATUS_PENDING:
                    Toast.makeText(this, "Transaction Pending ID : " + result.getResponse().getTransactionId(), Toast.LENGTH_SHORT).show();
                    break;
                case TransactionResult.STATUS_FAILED:
                    Toast.makeText(this, "Transaction Failed ID : " + result.getResponse().getTransactionId(), Toast.LENGTH_SHORT).show();
                    break;

            }

            result.getResponse().getValidationMessages();
        } else if (result.isTransactionCanceled()) {
            Toast.makeText(this, "Transaction Cancelled", Toast.LENGTH_SHORT).show();
        } else {
            if (result.getStatus().equalsIgnoreCase(TransactionResult.STATUS_INVALID)) {
                Toast.makeText(this, "Transaction Invalid", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Transaction Finished with failure", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
