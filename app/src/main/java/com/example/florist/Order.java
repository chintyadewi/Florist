package com.example.florist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Order extends AppCompatActivity {

    private TextView txtName, txtPrice, txtImgUrl;
    private ImageView imgFlower;
    private EditText editQuantity;

    int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        txtName=findViewById(R.id.txt_name);
        txtPrice=findViewById(R.id.txt_price);
        imgFlower=findViewById(R.id.img_flower_order);
        editQuantity=findViewById(R.id.edit_quantity);
        txtImgUrl=findViewById(R.id.txt_img_url);

//        get String array berdasarkan key
        String[] stringArray=getIntent().getStringArrayExtra(HomeFragment.Key_HomeFragment);
//        set value to textview
        txtName.setText(stringArray[0]);
        txtPrice.setText("Rp"+stringArray[1]);
        Picasso.get().load(stringArray[2]).into(imgFlower);
        txtImgUrl.setText(stringArray[2]);
    }

    public void decreaseQuantity(View view){
        quantity=Integer.parseInt(editQuantity.getText().toString());
        quantity=quantity-1;
        displayQuantity(quantity);
    }

    public void increaseQuantity(View view){
        quantity=Integer.parseInt(editQuantity.getText().toString());
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
        String name=txtName.getText().toString();
        String price=txtPrice.getText().toString().substring(2);
        String quantity=editQuantity.getText().toString();
        String imgUrl=txtImgUrl.getText().toString();
        Integer total_price=Integer.parseInt(price)*Integer.parseInt(quantity);

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("price", price);
        bundle.putString("quantity", quantity);
        bundle.putString("imgUrl", imgUrl);

    // set Fragmentclass Arguments
        BasketFragment basketFragment = new BasketFragment();
        basketFragment.setArguments(bundle);

        AlertDialog.Builder alertSuccess=new AlertDialog.Builder(this);
        alertSuccess.setTitle("Order Status");
        alertSuccess.setMessage("Your order is successful and will be processed\n\n" +
                "Name: "+name+"\nPrice: Rp"+price+"\nQuantity: "+quantity+"\nTotal Price: Rp"+total_price)
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
}
