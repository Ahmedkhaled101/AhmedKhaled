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
import com.example.ahmedkhaled.model.categories.Categories;
import com.example.ahmedkhaled.model.categories.Category;
import com.example.ahmedkhaled.model.home.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private Context context;
    private ArrayList<Category> categoriesArrayList = new ArrayList<>();
    private GetCategoryPositionInterface getCategoryPositionInterface;

    public CategoriesAdapter(Context context, ArrayList<Category> categoriesArrayList, GetCategoryPositionInterface getCategoryPositionInterface) {
        this.context = context;
        this.categoriesArrayList = categoriesArrayList;
        this.getCategoryPositionInterface = getCategoryPositionInterface;
    }

    @NonNull
    @NotNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoriesViewHolder holder, int position) {

        holder.category_title.setText(categoriesArrayList.get(position).getName());
        Glide.with(context).load(categoriesArrayList.get(position).getAvatar()).into(holder.category_iv);
    }

    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }

    public void setList(ArrayList<Category> categoriesArrayList) {
        this.categoriesArrayList = categoriesArrayList;
        notifyDataSetChanged();
    }

    protected class CategoriesViewHolder extends RecyclerView.ViewHolder {

        private ImageView category_iv;
        private TextView category_title;

        public CategoriesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            category_iv = itemView.findViewById(R.id.category_iv);
            category_title = itemView.findViewById(R.id.category_title_tv);

            category_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getCategoryPositionInterface.getCategoryPosition(getAdapterPosition());
                }
            });
        }
    }

    public interface GetCategoryPositionInterface {
        void getCategoryPosition(int position);
    }
}
