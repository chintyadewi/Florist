package com.example.florist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.florist.adapters.FlowerAdapter;
import com.example.florist.generator.ServiceGenerator;
import com.example.florist.models.Flower;
import com.example.florist.services.FlowerService;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements FlowerAdapter.onFlowerClickListener {

    private final String TAG=HomeFragment.class.getName();
    public static final String Key_HomeFragment="Key_HomeFragment";

    public View view;
    public RecyclerView rv;
    public FlowerAdapter flowerAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public List<Flower> flowers=new ArrayList<>();
    private FlowerService flowerService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home_fragment, container, false);
        rv=view.findViewById(R.id.rvFlower);

        flowerService = ServiceGenerator.createService(FlowerService.class);

        Call<List<Flower>> caloriesCall = flowerService.getFlower();

        final HomeFragment listener=this;

        caloriesCall.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                flowers = response.body();
                flowerAdapter=new FlowerAdapter(flowers);

                flowerAdapter.setListener(listener);
                layoutManager=new GridLayoutManager(getActivity().getApplicationContext(), 2);
                rv.setAdapter(flowerAdapter);
                rv.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {
                Snackbar.make(view, "Oops!", Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    @Override
    public void onClick(View view, int position) {
        Flower flower=flowers.get(position);
        Intent intent = new Intent(getActivity().getApplicationContext(), Order.class);
        intent.putExtra(Key_HomeFragment, new String[] {flower.getId().toString(), flower.getName(), flower.getPrice().toString(), flower.getImageUrl()});
        startActivity(intent);
    }
}
