package com.example.project_thuc_tap.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_thuc_tap.R;
import com.example.project_thuc_tap.activity.DetailProduct;
import com.example.project_thuc_tap.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {

    Context context; //màn hình mà muốn đổ vào
    ArrayList<Product> arrayList;
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_product, null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    public ProductAdapter(Context context, ArrayList<Product> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    //ho trợ get set các thuộc tính gắn lên layout
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Product product = arrayList.get(position);
        holder.tvTenSp.setText(product.getTenSP());
        holder.tvXepLoai.setText(String.valueOf(product.getRate()));
        //TODO chưa ánh xạ thằng ảnh new tĩnh với là thằng ngôi sao
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiaSp.setText("Price: "+String.valueOf(decimalFormat.format(product.getGiaSp()))+" Đ");
        Picasso.with(context).load(product.getHinhAnhSp()).placeholder(R.drawable.warning).error(R.drawable.warning).into(holder.imgHinhSanPham);
        holder.parentLayout.setOnClickListener(v -> {
            //TODO
            Intent intent = new Intent(context, DetailProduct.class);
            intent.putExtra("thongtinsp", arrayList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgHinhSanPham, imgNewStatic;
        public TextView tvTenSp, tvGiaSp, tvXepLoai;
        ConstraintLayout parentLayout;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhSanPham = itemView.findViewById(R.id.imgLineProduct);
            tvTenSp = itemView.findViewById(R.id.tvLineNameProduct);
            tvGiaSp = itemView.findViewById(R.id.tvLinePrice);
            tvXepLoai = itemView.findViewById(R.id.tvRateProduct);
            parentLayout = itemView.findViewById(R.id.lineProduct);
        }
    }


}
