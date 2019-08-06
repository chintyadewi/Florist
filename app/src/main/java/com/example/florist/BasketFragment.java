package com.example.florist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.florist.adapters.BasketAdapter;
import com.example.florist.generator.ServiceGenerator;
import com.example.florist.models.Basket;
import com.example.florist.services.BasketService;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasketFragment extends Fragment{

    private String name, imgUrl;
    private Integer price, quantity, totalPrice;

    public View view;
    public TextView txtTotal;
    public RecyclerView rv;
    public BasketAdapter basketAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public List<Basket> listBasket=new ArrayList<>();
    private BasketService basketService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_basket, container, false);
        rv=view.findViewById(R.id.rvBasket);
        txtTotal=view.findViewById(R.id.txt_total);

        basketService = ServiceGenerator.createService(BasketService.class);

        Call<List<Basket>> basketCall = basketService.getBasketItem(1);

        basketCall.enqueue(new Callback<List<Basket>>() {
            @Override
            public void onResponse(Call<List<Basket>> call, Response<List<Basket>> response) {
                listBasket = response.body();
                Integer total=0;
                for (Basket b: listBasket){
                    total+=b.getTotalPrice();
                }
                txtTotal.setText("Total Rp"+total.toString());
                basketAdapter=new BasketAdapter(listBasket);
                layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
                rv.setAdapter(basketAdapter);
                rv.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Basket>> call, Throwable t) {
                Snackbar.make(view, "Oops!", Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
