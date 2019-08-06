package com.example.florist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.florist.generator.ServiceGenerator;
import com.example.florist.models.Basket;
import com.example.florist.services.BasketService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Integer.parseInt;

public class Order extends AppCompatActivity {

    private TextView txtId, txtName, txtPrice, txtImgUrl;
    private ImageView imgFlower;
    private EditText editQuantity;
    private BasketService basketService;
    private Basket basket;
    private SharedPreferences sharedPrefs;
    public static final String ID_USER = "idUser";
    int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        sharedPrefs = getSharedPreferences("loginSharedPref", Context.MODE_PRIVATE);

        txtId=findViewById(R.id.txt_id);
        txtName=findViewById(R.id.txt_name);
        txtPrice=findViewById(R.id.txt_price);
        imgFlower=findViewById(R.id.img_flower_order);
        editQuantity=findViewById(R.id.edit_quantity);
        txtImgUrl=findViewById(R.id.txt_img_url);

//        get String array berdasarkan key
        String[] stringArray=getIntent().getStringArrayExtra(HomeFragment.Key_HomeFragment);
//        set value to textview
        txtId.setText(stringArray[0]);
        txtName.setText(stringArray[1]);
        txtPrice.setText("Rp"+stringArray[2]);
        Picasso.get().load(stringArray[3]).into(imgFlower);
        txtImgUrl.setText(stringArray[3]);

        basketService = ServiceGenerator.createService(BasketService.class);
    }

    public void decreaseQuantity(View view){
        quantity= parseInt(editQuantity.getText().toString());
        quantity=quantity-1;
        displayQuantity(quantity);
    }

    public void increaseQuantity(View view){
        quantity= parseInt(editQuantity.getText().toString());
        quantity=quantity+1;
        displayQuantity(quantity);
    }

    private void displayQuantity(int number) {
        if (number<0){
            editQuantity.setText(""+0);
        }else{
            editQuantity.setText(""+number);
        }
    }

    public void clickBuy(View view){
        Integer getIdUser=sharedPrefs.getInt(ID_USER,0);
        final String idUser=getIdUser.toString();
        final String idProduct=txtId.getText().toString();
        final String name=txtName.getText().toString();
        final String price=txtPrice.getText().toString().substring(2);
        final Integer quantity=parseInt(editQuantity.getText().toString());
        final Integer total_price= parseInt(price)* quantity;

        basket=new Basket("", idUser, idProduct, quantity,total_price);
        final AlertDialog.Builder alertSuccess=new AlertDialog.Builder(this);

    // set Fragmentclass Arguments
        Call<Basket> postBasketCall=this.basketService.addBasketItem(basket);
        postBasketCall.enqueue(new Callback<Basket>() {
            @Override
            public void onResponse(Call<Basket> call, Response<Basket> response) {
                alertSuccess.setTitle("Order Status");
                alertSuccess.setMessage("Your order is successful and will be processed\n\n" +
                        "Name: "+name+" "+idUser+"\nPrice: Rp"+price+"\nQuantity: "+quantity+"\nTotal Price: Rp"+total_price)
                        .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(Order.this, Catalogue.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog alertDialog=alertSuccess.create();
                alertDialog.show();
            }

            @Override
            public void onFailure(Call<Basket> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure to send your order", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
