package com.example.project_thuc_tap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_thuc_tap.R;
import com.example.project_thuc_tap.activity.TabHome;
import com.example.project_thuc_tap.activity.TabShopping;
import com.example.project_thuc_tap.model.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

//adapter cho ListView

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Cart> carts;

    public CartAdapter(Context context, ArrayList<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @Override
    public int getCount() {
        return carts.size();
    }

    @Override
    public Object getItem(int position) {
        return carts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        //Todo
        TextView tvNameItemCart, tvTotalItemCart, tvDescribeItemCart, tvQuantityItemCart;
        ImageButton btnAddItemCart, btnSubItemCart;
        ImageView imgItemCart;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view = null;
        if(convertView == null){
            view = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.line_cart, null);
            view.tvNameItemCart = (TextView) convertView.findViewById(R.id.tvNameItemCart);
            view.tvTotalItemCart = (TextView) convertView.findViewById(R.id.tvTotalItemCart);
            view.tvDescribeItemCart = (TextView) convertView.findViewById(R.id.tvDescribeItemCart);
            view.tvQuantityItemCart = (TextView) convertView.findViewById(R.id.tvQuantitiItemCart);
            view.btnAddItemCart = (ImageButton) convertView.findViewById(R.id.btnAddItemCart);
            view.btnSubItemCart = (ImageButton) convertView.findViewById(R.id.btnSubItemCart);
            view.imgItemCart = (ImageView)  convertView.findViewById(R.id.imgItemCart);
            convertView.setTag(view);
        }else{
            view = (ViewHolder) convertView.getTag();
        }
        Cart cart = (Cart) getItem(position);
        view.tvNameItemCart.setText(cart.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        view.tvTotalItemCart.setText(decimalFormat.format(cart.getGiasp()) + "Đ");
        view.tvQuantityItemCart.setText(String.valueOf(cart.getSoluong()));
        Picasso.with(context).load(cart.getHinhanh()).placeholder(R.drawable.warning)
                .error(R.drawable.warning).into(view.imgItemCart);
        int sl = Integer.valueOf(view.tvQuantityItemCart.getText().toString());//lay sl hieejn taji

        if(sl >= 16){
            view.btnAddItemCart.setVisibility(View.INVISIBLE);
            view.btnSubItemCart.setVisibility(View.VISIBLE);
        }else if(sl <= 1){
            view.btnAddItemCart.setVisibility(View.VISIBLE);
            view.btnSubItemCart.setVisibility(View.INVISIBLE);
        }else if(sl >= 1){
            view.btnAddItemCart.setVisibility(View.VISIBLE);
            view.btnSubItemCart.setVisibility(View.VISIBLE);
        }
        //xử lí khi bấm nút cộng trừ trong giỏ hàng thì giá sẽ cập nhật luôn
        ViewHolder finalView = view;
        view.btnAddItemCart.setOnClickListener(v -> {
            int slMoiNhat = Integer.valueOf(finalView.tvQuantityItemCart.getText().toString()) + 1;
            int slHienTai = TabHome.carts.get(position).getSoluong();
            long giaHienTai = TabHome.carts.get(position).getGiasp();
            TabHome.carts.get(position).setSoluong(slMoiNhat);
            long giaMoiNhat = (giaHienTai * slMoiNhat) / slHienTai;
            TabHome.carts.get(position).setGiasp(giaMoiNhat);
            finalView.tvTotalItemCart.setText(decimalFormat.format(giaMoiNhat) + "Đ");
            TabShopping.EvenUlties();
            if(slMoiNhat > 16){
                finalView.btnAddItemCart.setVisibility(View.INVISIBLE);
                finalView.btnSubItemCart.setVisibility(View.VISIBLE);
                finalView.tvQuantityItemCart.setText(String.valueOf(slMoiNhat));
            }else {
                finalView.btnAddItemCart.setVisibility(View.VISIBLE);
                finalView.btnSubItemCart.setVisibility(View.VISIBLE);
                finalView.tvQuantityItemCart.setText(String.valueOf(slMoiNhat));
            }
        });

        view.btnSubItemCart.setOnClickListener(v -> {
            int slMoiNhat = Integer.valueOf(finalView.tvQuantityItemCart.getText().toString()) - 1;
            int slHienTai = TabHome.carts.get(position).getSoluong();
            long giaHienTai = TabHome.carts.get(position).getGiasp();
            TabHome.carts.get(position).setSoluong(slMoiNhat);
            long giaMoiNhat = (giaHienTai * slMoiNhat) / slHienTai;
            TabHome.carts.get(position).setGiasp(giaMoiNhat);
            finalView.tvTotalItemCart.setText(decimalFormat.format(giaMoiNhat) + "Đ");
            TabShopping.EvenUlties();
            if(slMoiNhat < 2){
                finalView.btnAddItemCart.setVisibility(View.VISIBLE);
                finalView.btnSubItemCart.setVisibility(View.INVISIBLE);
                finalView.tvQuantityItemCart.setText(String.valueOf(slMoiNhat));
            }else {
                finalView.btnAddItemCart.setVisibility(View.VISIBLE);
                finalView.btnSubItemCart.setVisibility(View.VISIBLE);
                finalView.tvQuantityItemCart.setText(String.valueOf(slMoiNhat));
            }
        });

        return convertView;
    }
}
