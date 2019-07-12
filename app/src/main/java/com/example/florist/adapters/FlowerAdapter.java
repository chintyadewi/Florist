package com.example.florist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.florist.R;
import com.example.florist.models.Flower;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder> {

    private List<Flower> listFlower=new ArrayList<>();
    public FlowerAdapter(List<Flower> listFlower){
        this.listFlower=listFlower;
    }

    private onFlowerClickListener listener;

    public interface onFlowerClickListener{
        void onClick(View view, int position);
    }

    public void setListener(onFlowerClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View vh= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_flower, viewGroup, false);
        FlowerViewHolder viewHolder=new FlowerViewHolder(vh);
        return viewHolder;
    }

//    OnbindView
    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder flowerViewHolder, int i) {
        Flower item=listFlower.get(i);
        flowerViewHolder.txtName.setText(item.getName());
        flowerViewHolder.txtPrice.setText("Rp"+item.getPrice());
        Picasso.get().load(item.getImageUrl()).placeholder(R.drawable.ic_launcher_background).into(flowerViewHolder.imageFlower);
    }

    @Override
    public int getItemCount() {
        return listFlower.size();
    }

    public class FlowerViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageFlower;
        public TextView txtName, txtPrice;

        public FlowerViewHolder(@NonNull View itemView){
            super(itemView);
            imageFlower=itemView.findViewById(R.id.img_flower);
            txtName=itemView.findViewById(R.id.txt_name);
            txtPrice=itemView.findViewById(R.id.txt_price);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    listener.onClick(v, getAdapterPosition());
                }
            });
        }
    }
}
