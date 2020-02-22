package com.dzaky.midtrans_dzakdzaks;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<Product> list;
    private Activity activity;

    public ProductAdapter(List<Product> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_products, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImages())
                .into(holder.imageView);

        holder.tvName.setText(list.get(position).getName());
        holder.tvPrice.setText("Rp. " + list.get(position).getPrice());
        holder.tvRating.setText(String.valueOf(list.get(position).getRating()));

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
            intent.putExtra("image", list.get(position).getImages());
            intent.putExtra("name", list.get(position).getName());
            intent.putExtra("price", list.get(position).getPrice());
            intent.putExtra("qty", list.get(position).getQty());
            intent.putExtra("rating", list.get(position).getRating());
            activity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvRating;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_product);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvRating = itemView.findViewById(R.id.tv_rating);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
