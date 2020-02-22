package com.dzaky.midtrans_dzakdzaks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dzaky.midtrans_dzakdzaks.widgets.WidgetsTextView;

public class ProductDetailActivity extends AppCompatActivity {

    private String image, name;
    private int price, qty;
    private double rating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        init();
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
                    .into(((ImageView) findViewById(R.id.image_product_main)));

            ((WidgetsTextView) findViewById(R.id.product_name)).setText(name);
            ((WidgetsTextView) findViewById(R.id.product_price)).setText("Rp. " + price);
            ((WidgetsTextView) findViewById(R.id.product_rating)).setText(String.valueOf(rating));

            findViewById(R.id.button_primary).setOnClickListener(view -> {
                Intent intent = new Intent(this, ProductOrderActivity.class);
                intent.putExtra("image", image);
                intent.putExtra("name", name);
                intent.putExtra("price", price);
                intent.putExtra("qty", qty);
                intent.putExtra("rating", rating);
                startActivity(intent);
            });
        }
    }
}
