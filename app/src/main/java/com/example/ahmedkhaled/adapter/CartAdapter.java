package com.example.ahmedkhaled.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ahmedkhaled.R;
import com.example.ahmedkhaled.model.cart.ProductInCart;
import com.example.ahmedkhaled.model.cart.Product;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private CartInterface cartInterface;
    private ArrayList<Product> productInCarts = new ArrayList<>();

    public CartAdapter(Context context, CartInterface cartInterface, ArrayList<Product> productInCarts) {
        this.context = context;
        this.cartInterface = cartInterface;
        this.productInCarts = productInCarts;
    }

    @NonNull
    @NotNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_incart_rv_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartViewHolder holder, int position) {
        holder.productInCart_title_tv.setText(productInCarts.get(position).getProduct().getTitle());
        holder.productInCart_details_tv.setText(productInCarts.get(position).getProduct().getDescription());
        holder.productInCart_price_tv.setText(productInCarts.get(position).getTotalText());
        holder.counterInCart_tv.setText(String.valueOf(productInCarts.get(position).getAmount()));
        Glide.with(context).load(productInCarts.get(position).getProduct().getAvatar()).into(holder.productInCart_iv);
    }

    @Override
    public int getItemCount() {
        return productInCarts.size();
    }

    public void setList(ArrayList<Product> productInCarts) {
        this.productInCarts = productInCarts;
        notifyDataSetChanged();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        private ImageView productInCart_iv;
        private TextView productInCart_title_tv, productInCart_details_tv, productInCart_price_tv;
        private MaterialTextView counterInCart_tv;

        public CartViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            productInCart_iv = itemView.findViewById(R.id.productInCart_iv);
            productInCart_title_tv = itemView.findViewById(R.id.productInCart_title_tv);
            productInCart_details_tv = itemView.findViewById(R.id.productInCart_details_tv);
            productInCart_price_tv = itemView.findViewById(R.id.productInCart_price_tv);
            counterInCart_tv = itemView.findViewById(R.id.counterInCart_tv);
        }
    }

    public interface CartInterface {
        void getCartPosition(int position);
    }
}
