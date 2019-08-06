package com.example.florist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class AccountFragment extends Fragment implements View.OnClickListener {

    private SharedPreferences sharedPrefs;
    public static final String NOT_LOGIN = "notLogin";
    public static final String ID_USER = "idUser";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefs = getActivity().getSharedPreferences("loginSharedPref", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_account, container, false);
        Button l = (Button) v.findViewById(R.id.logout);
        l.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor=sharedPrefs.edit();
        editor.remove(ID_USER);
        editor.remove(NOT_LOGIN);
        editor.apply();
        Intent i=new Intent(getContext(), Welcome.class);
        startActivity(i);
        getActivity().finish();
    }
}
