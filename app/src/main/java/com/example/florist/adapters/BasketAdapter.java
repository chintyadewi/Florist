package com.example.florist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.florist.R;
import com.example.florist.generator.ServiceGenerator;
import com.example.florist.models.Basket;
import com.example.florist.models.Flower;
import com.example.florist.services.FlowerService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Integer.parseInt;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private List<Basket> listBasket=new ArrayList<>();
    public BasketAdapter(List<Basket> listBasket){
        this.listBasket=listBasket;
    }
    public List<Flower> flowers=new ArrayList<>();
    private FlowerService flowerService;

    @NonNull
    @Override
    public BasketAdapter.BasketViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View vh= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_basket, viewGroup, false);
        BasketAdapter.BasketViewHolder viewHolder=new BasketViewHolder(vh);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BasketAdapter.BasketViewHolder basketViewHolder, int i) {
        final Basket item=listBasket.get(i);
        String id_product=item.getIdProduct();
        flowerService = ServiceGenerator.createService(FlowerService.class);

        Call<List<Flower>> caloriesCall = flowerService.getFlowerDetail(parseInt(id_product));

        caloriesCall.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                flowers = response.body();
                for(Flower f: flowers){
                    basketViewHolder.txtName.setText(f.getName());
                    basketViewHolder.txtPrice.setText("Rp"+f.getPrice().toString());;
                    basketViewHolder.txtQuantity.setText(item.getQuantity().toString());;
                    basketViewHolder.txtTotalPrice.setText("Rp"+item.getTotalPrice().toString());
                    Picasso.get().load(f.getImageUrl()).placeholder(R.drawable.ic_launcher_background).into(basketViewHolder.imageFlower);
                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listBasket.size();
    }

    public class BasketViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageFlower;
        public TextView txtName, txtPrice, txtQuantity, txtTotalPrice;

        public BasketViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFlower=itemView.findViewById(R.id.imgFlower);
            txtName=itemView.findViewById(R.id.txt_name);
            txtPrice=itemView.findViewById(R.id.price);
            txtQuantity=itemView.findViewById(R.id.quantity);
            txtTotalPrice=itemView.findViewById(R.id.total_price);
        }
    }
}
