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
import com.example.ahmedkhaled.databinding.ProductRvItemBinding;
import com.example.ahmedkhaled.model.home.Product;
import com.example.ahmedkhaled.model.home.Products;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private Context context;
    private ProductsAdapterPositionInterface positionInterface;
    private ArrayList<Product> productsList = new ArrayList<Product>();

    public ProductsAdapter(Context context, ProductsAdapterPositionInterface positionInterface, ArrayList<Product> productsList) {
        this.context = context;
        this.positionInterface = positionInterface;
        this.productsList = productsList;
    }

    @NonNull
    @NotNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_rv_item, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductsViewHolder holder, int position) {
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

    class ProductsViewHolder extends RecyclerView.ViewHolder {
        private ImageView product_iv;
        private TextView product_title_tv, product_details_tv, product_price_tv;
        private ImageButton add_product;

        public ProductsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            product_iv = itemView.findViewById(R.id.product_iv);
            product_title_tv = itemView.findViewById(R.id.product_title_tv);
            product_details_tv = itemView.findViewById(R.id.product_details_tv);
            product_price_tv = itemView.findViewById(R.id.product_price_tv);
            add_product = itemView.findViewById(R.id.add_product_ib);

            product_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    positionInterface.getProductsAdapterPosition(getAdapterPosition());
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

    public interface ProductsAdapterPositionInterface {
        void getProductsAdapterPosition(int position);
        void addProductInCart(int position);
    }

}
