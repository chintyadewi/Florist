package com.example.florist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.florist.adapters.BasketAdapter;
import com.example.florist.adapters.FlowerAdapter;
import com.example.florist.models.Basket;
import com.example.florist.models.Flower;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment{

    private String name, imgUrl;
    private Integer price, quantity, totalPrice;

    public View view;
    public RecyclerView rv;
    public BasketAdapter basketAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public List<Basket> listBasket=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString("name");
            price=Integer.parseInt(getArguments().getString("price"));
            quantity=Integer.parseInt(getArguments().getString("quantity"));
            totalPrice=price*quantity;
            imgUrl=getArguments().getString("imgUrl");

            Log.d("lkjsd", name);
            Log.d("lkjsd", ""+price);
            Log.d("lkjsd", ""+quantity);
            Log.d("lkjsd", ""+totalPrice);
            Log.d("lkjsd", imgUrl);

            listBasket.add(new Basket(name, price, quantity, totalPrice, imgUrl));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_basket, container, false);
        rv=view.findViewById(R.id.rvBasket);

        if (getArguments() != null) {
            name = getArguments().getString("name");
            price=Integer.parseInt(getArguments().getString("price"));
            quantity=Integer.parseInt(getArguments().getString("quantity"));
            totalPrice=price*quantity;
            imgUrl=getArguments().getString("imgUrl");

            Log.d("lkjsd", name);
            Log.d("lkjsd", ""+price);
            Log.d("lkjsd", ""+quantity);
            Log.d("lkjsd", ""+totalPrice);
            Log.d("lkjsd", imgUrl);

            listBasket.add(new Basket(name, price, quantity, totalPrice, imgUrl));
        }

        listBasket.add(new Basket("Red Rose Bouquet", 20000, 3, 60000,
                "https://www.archiesonline.com/public/images/product/large/Red_Roses_Bouquet_89070890046682_841bb04a.jpg"));

        basketAdapter=new BasketAdapter(listBasket);
        layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setAdapter(basketAdapter);
        rv.setLayoutManager(layoutManager);
        return view;
    }
}
