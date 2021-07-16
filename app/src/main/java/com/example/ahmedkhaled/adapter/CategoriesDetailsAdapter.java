package com.example.ahmedkhaled.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ahmedkhaled.R;
import com.example.ahmedkhaled.model.home.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoriesDetailsAdapter extends RecyclerView.Adapter<CategoriesDetailsAdapter.CategoriesDetailsViewHolder> {

    private Context context;
    private CategoriesDetailsAdapterPositionInterface positionInterface;
    private ArrayList<Product> productsList = new ArrayList<Product>();

    public CategoriesDetailsAdapter(Context context, CategoriesDetailsAdapterPositionInterface positionInterface, ArrayList<Product> productsList) {
        this.context = context;
        this.positionInterface = positionInterface;
        this.productsList = productsList;
    }

    @NonNull
    @NotNull
    @Override
    public CategoriesDetailsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_rv_item, parent, false);
        return new CategoriesDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoriesDetailsViewHolder holder, int position) {
        holder.product_title_tv.setText(productsList.get(position).getTitle());
        holder.product_details_tv.setText(productsList.get(position).getDescription());
        holder.product_price_tv.setText(String.valueOf(productsList.get(position).getPrice()));
        Glide.with(context).load(productsList.get(position).getAvatar()).into(holder.product_iv);
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void setList(ArrayList<Product> productsList) {
        this.productsList = productsList;
        notifyDataSetChanged();
    }

    class CategoriesDetailsViewHolder extends RecyclerView.ViewHolder {
        private ImageView product_iv;
        private TextView product_title_tv, product_details_tv, product_price_tv;
        private ImageButton add_product;

        public CategoriesDetailsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            product_iv = itemView.findViewById(R.id.product_iv);
            product_title_tv = itemView.findViewById(R.id.product_title_tv);
            product_details_tv = itemView.findViewById(R.id.product_details_tv);
            product_price_tv = itemView.findViewById(R.id.product_price_tv);
            add_product = itemView.findViewById(R.id.add_product_ib);

            product_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    positionInterface.getCategoriesDetailsAdapterPosition(getAdapterPosition());
                }
            });

            add_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    positionInterface.addProductInCart(getAdapterPosition());
                }
            });
        }
    }

    public interface CategoriesDetailsAdapterPositionInterface {
        void getCategoriesDetailsAdapterPosition(int position);
        void addProductInCart(int position);
    }

}
