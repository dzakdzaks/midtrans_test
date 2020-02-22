package com.dzaky.midtrans_dzakdzaks;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        init();
    }

    private void init() {
        findViewById(R.id.image_setting_account).setOnClickListener(view -> {
            startActivity(new Intent(this, AccountActivity.class));
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        ProductAdapter adapter = new ProductAdapter(CustomerData.getListProduct(), this);

        recyclerView.setAdapter(adapter);
    }
}
