package com.example.project_thuc_tap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_thuc_tap.R;
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


        return convertView;
    }
}
