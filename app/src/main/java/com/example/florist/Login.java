package com.example.florist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.florist.generator.ServiceGenerator;
import com.example.florist.models.User;
import com.example.florist.services.LoginService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private LoginService loginService;
    public List<User> users=new ArrayList<>();
    private SharedPreferences sharedPrefs;
    public static final String NOT_LOGIN = "notLogin";
    public static final String ID_USER = "idUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.edit_username);
        editTextPassword = findViewById(R.id.edit_password);
        loginService = ServiceGenerator.createService(LoginService.class);

        sharedPrefs = getSharedPreferences("loginSharedPref", Context.MODE_PRIVATE);
        if(sharedPrefs.getBoolean(NOT_LOGIN, false)){
            Intent i=new Intent(this, Catalogue.class);
            startActivity(i);
            finish();
        }
    }

    public void clickLogin(View view){
        if(TextUtils.isEmpty(editTextUsername.getText().toString().trim())){
            Toast.makeText(view.getContext(), "Username tidak boleh kosong!", Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(editTextPassword.getText().toString().trim())){
            Toast.makeText(view.getContext(), "Password tidak boleh kosong!", Toast.LENGTH_LONG).show();
        }
        else if(isValidUsername(editTextUsername.getText().toString())){
            Toast.makeText(view.getContext(), "Username invalid!", Toast.LENGTH_LONG).show();
        }
        else {
            Call<List<User>> usersCall = loginService.getUsers();
            usersCall.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    users = response.body();
                    for (User u:users) {
                        String username = u.getUsername();
                        String password = u.getPassword();
                        String edtUsername = editTextUsername.getText().toString();
                        String edtPassword = editTextPassword.getText().toString();

                        if (username.equals(edtUsername) && password.equals(edtPassword)) {
                            SharedPreferences.Editor editor=sharedPrefs.edit();
                            editor.putBoolean(NOT_LOGIN, false);
                            editor.putInt(ID_USER, u.getId());
                            editor.apply();

                            Intent i = new Intent(Login.this, Catalogue.class);
                            startActivity(i);
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failure to connect!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static boolean isValidUsername(String username) {
        boolean isValid = false;

        String expression = "/^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$/";
        CharSequence inputStr = username;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
