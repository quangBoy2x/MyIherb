package com.example.project_thuc_tap.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.project_thuc_tap.R;
import com.example.project_thuc_tap.TabHome;
import com.example.project_thuc_tap.TabShopping;
import com.example.project_thuc_tap.model.Cart;
import com.example.project_thuc_tap.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DetailProduct extends AppCompatActivity {

    Toolbar toolbarDetail;
    ImageView imgDetailProduct, btnAdd, btnSubtract;
    TextView tvDetailName, tvDetailPrice, tvDetailDescribe, tvDetailRate, tvQuantity;
    Button btnAddToCart;
    int totalQuantity = 0; //default quantity number add to cart
    int id = 0;
    //detail product
    String name = "";
    int price = 0;
    String img = "";
    String describe = "";
    int idSP = 0;
    int rate = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

//        Intent intent = getIntent();
//        String id  = "";
        Map();
        GetInformationProduct();
        AddItem();
        SubtractItem();
        AddToCart();

    }


    void AddToCart(){
        btnAddToCart.setOnClickListener(v -> {
            if(TabHome.carts.size() > 0){
                //kiem tra carts co update hay khong
                boolean flag = false;
                for(int i = 0; i<TabHome.carts.size(); i++){
                    if(TabHome.carts.get(i).getId() == id){
                        TabHome.carts.get(i).setSoluong(TabHome.carts.get(i).getSoluong() + totalQuantity);

                        //kiểm tra sau khi upadet số lượng mua cơ mà lớn qua cho về 15
                        if(TabHome.carts.get(i).getSoluong()>=15){
                            TabHome.carts.get(i).setSoluong(15);
                        }

                        //tổng tiền của toàn bộ sản phẩm
                        TabHome.carts.get(i).setGiasp(price*TabHome.carts.get(i).getSoluong());
                        flag = true;
                    }
                }

                if(flag = false){
                    long giaMoi = totalQuantity * price;
                    TabHome.carts.add(new Cart(id,name, giaMoi, img, totalQuantity));
                }


            }else {
                //neu chua co gi thi tao moi
                long giaMoi = totalQuantity * price;
//                int id, String tensp, long giasp, String hinhanh, int soluong
                TabHome.carts.add(new Cart(id,name, giaMoi, img, totalQuantity));
            }

            Intent intent = new Intent(getApplicationContext(), TabShopping.class);
            startActivity(intent);
        });
    }


    private void GetInformationProduct() {

        Product product = (Product) getIntent().getSerializableExtra("thongtinsp");
        id = product.getID();
        name = product.getTenSP();
        price = product.getGiaSp();
        rate = product.getRate();
        img = product.getHinhAnhSp();
        describe = product.getMotaSP();
        tvDetailRate.setText(String.valueOf(rate));
        tvDetailName.setText(name);
        tvDetailDescribe.setText(describe);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvDetailPrice.setText(String.valueOf(decimalFormat.format(price)) + "Đ");
        Picasso.with(getApplicationContext()).load(img).placeholder(R.drawable.warning).error(R.drawable.warning).into(imgDetailProduct);
        Log.d("Intent", "des"+describe);

    }

    void AddItem(){
        btnAdd.setOnClickListener(v -> {
            if(totalQuantity<=15){
                totalQuantity++;
                tvQuantity.setText(String.valueOf(totalQuantity));
            }
            else{
                Toast.makeText(this, "Your quantity is maximum", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void SubtractItem(){
        btnSubtract.setOnClickListener(v -> {
            if(totalQuantity>1){
                totalQuantity--;
                tvQuantity.setText(String.valueOf(totalQuantity));
            }
        });
    }


    void Map(){
        toolbarDetail = (Toolbar) findViewById(R.id.toolbar3);
        imgDetailProduct = (ImageView) findViewById(R.id.imgDetailProduct);
        tvDetailName = (TextView) findViewById(R.id.tvDetailName);
        tvDetailPrice = (TextView) findViewById(R.id.tvDetailPrice);
        tvDetailRate = (TextView) findViewById(R.id.tvDetailRate);
        btnAddToCart = (Button) findViewById(R.id.btnAddToCart);
        btnAdd = (ImageView) findViewById(R.id.btnAddItem);
        btnSubtract = (ImageView) findViewById(R.id.btnSubtractItem);
        tvDetailDescribe = (TextView) findViewById(R.id.tvDetailDescribe);
        tvQuantity = (TextView) findViewById(R.id.tvquantity);


    }

}