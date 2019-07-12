package com.example.florist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.florist.adapters.FlowerAdapter;
import com.example.florist.models.Flower;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements FlowerAdapter.onFlowerClickListener {

    private final String TAG=HomeFragment.class.getName();
    public static final String Key_HomeFragment="Key_HomeFragment";

    public View view;
    public RecyclerView rv;
    public FlowerAdapter flowerAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public List<Flower> listFlower=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home_fragment, container, false);
        rv=view.findViewById(R.id.rvFlower);
        listFlower.add(new Flower("White Rose Bouquet",
                40000, "https://cdn.shopify.com/s/files/1/1060/3816/products/02020302_Promise_White_Rose_Bouquet.jpeg?v=1526082961"));
        listFlower.add(new Flower("Pink Rose Bouquet",
                30000, "https://www.florajapan.com/images/thumbnails/465/465/detailed/4/New-Rose1.jpg"));
        listFlower.add(new Flower("Red Rose Bouquet",
                20000, "https://www.archiesonline.com/public/images/product/large/Red_Roses_Bouquet_89070890046682_841bb04a.jpg"));
        listFlower.add(new Flower("Mix Rose Bouquet",
                35000, "https://jkflorist.com/wp-content/uploads/2016/12/Bunch-Flowers-to-Sikkim-India1.jpg"));
        listFlower.add(new Flower("White Rose Bouquet",
                40000, "https://cdn.shopify.com/s/files/1/1060/3816/products/02020302_Promise_White_Rose_Bouquet.jpeg?v=1526082961"));
        listFlower.add(new Flower("Pink Rose Bouquet",
                30000, "https://www.florajapan.com/images/thumbnails/465/465/detailed/4/New-Rose1.jpg"));
        listFlower.add(new Flower("Red Rose Bouquet",
                20000, "https://www.archiesonline.com/public/images/product/large/Red_Roses_Bouquet_89070890046682_841bb04a.jpg"));
        listFlower.add(new Flower("Mix Rose Bouquet",
                35000, "https://jkflorist.com/wp-content/uploads/2016/12/Bunch-Flowers-to-Sikkim-India1.jpg"));

        flowerAdapter=new FlowerAdapter(listFlower);
        flowerAdapter.setListener(this);
        layoutManager=new GridLayoutManager(getActivity().getApplicationContext(), 2);
        rv.setAdapter(flowerAdapter);
        rv.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onClick(View view, int position) {
        Flower flower=listFlower.get(position);
        Intent intent = new Intent(getActivity().getApplicationContext(), Order.class);
        intent.putExtra(Key_HomeFragment, new String[] {flower.getName(), flower.getPrice().toString(), flower.getImageUrl()});
        startActivity(intent);
    }
}
