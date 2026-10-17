package com.example.androiduicompose;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAnimalAdapter
        extends RecyclerView.Adapter<RecyclerAnimalAdapter.AnimalViewHolder> {

    public interface OnAnimalNameClickListener {
        void onAnimalNameClick(Animal animal, int position);
    }

    private final List<Animal> animals;
    private final OnAnimalNameClickListener clickListener;

    public RecyclerAnimalAdapter(
            List<Animal> animals,
            OnAnimalNameClickListener clickListener
    ) {
        this.animals = animals;
        this.clickListener = clickListener;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return animals.get(position).getId();
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_animal, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animals.get(position);
        holder.name.setText(animal.getName());
        holder.image.setImageResource(animal.getImageResId());
        holder.image.setContentDescription(animal.getName() + " 图片");
        holder.name.setOnClickListener(v -> {
            int adapterPosition = holder.getBindingAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                clickListener.onAnimalNameClick(
                        animals.get(adapterPosition),
                        adapterPosition
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    static final class AnimalViewHolder extends RecyclerView.ViewHolder {
        final ImageView image;
        final TextView name;

        AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.recycler_image);
            name = itemView.findViewById(R.id.recycler_name);
        }
    }
}
