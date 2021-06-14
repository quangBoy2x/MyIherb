package com.example.project_thuc_tap.activity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project_thuc_tap.R;
import com.example.project_thuc_tap.adapter.CartAdapter;
import com.example.project_thuc_tap.ulities.Server;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabShopping#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabShopping extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnPurchase, btnContinueShopping;
    TextView tvDefaultIfNull;
    static TextView tvTotalPrice;
    TextView tvTotalProducts;
    ListView lvProducts;
    CartAdapter adapter;
    public static long totalMoney;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TabShopping() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabHome.
     */
    // TODO: Rename and change types and number of parameters
    public static TabShopping newInstance(String param1, String param2) {
        TabShopping fragment = new TabShopping();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_tab_shopping, container, false);
        //Map
        btnContinueShopping = (Button) v.findViewById(R.id.btnContinue);
        btnPurchase = (Button) v.findViewById(R.id.btnPurchase);
        tvDefaultIfNull = (TextView) v.findViewById(R.id.tvDefaultIfNull);
        tvTotalPrice = (TextView) v.findViewById(R.id.tvTotalPrice);
        tvTotalProducts = (TextView) v.findViewById(R.id.tvTotalProducts);
        lvProducts = (ListView) v.findViewById(R.id.lvProducts) ;
        adapter = new CartAdapter(getActivity(), TabHome.carts);
        lvProducts.setAdapter(adapter);
        CheckData();
        EvenUlties();
        Purchase();

        return v;
    }




    //cho pulic để sử dụng cho bên cartAdapter trường hợp update lại sản phẩm
    public static void EvenUlties() {
        long totalPrice = 0;
        for(int i = 0; i <TabHome.carts.size();i++){
            totalPrice += TabHome.carts.get(i).getGiasp();
        }
        totalMoney = totalPrice;
        DecimalFormat del = new DecimalFormat("###,###,###");
        tvTotalPrice.setText(del.format(totalPrice) + "Đ");
    }


    private void CheckData() {

        if(TabHome.carts.size() <=0){
//            adapter.notifyDataSetChanged();
            tvDefaultIfNull.setVisibility(View.VISIBLE);
            lvProducts.setVisibility(View.INVISIBLE);
        }else {
//            adapter.notifyDataSetChanged();
            tvDefaultIfNull.setVisibility(View.INVISIBLE);
            lvProducts.setVisibility(View.VISIBLE);
        }
    }
    //xu li khi mua hang
    //todo chua lam xong can hoan thien vao toi nay update don hang len server
    public void Purchase(){
        btnPurchase.setOnClickListener(v -> {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            //gui cho sever voi dang string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlBill, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("idBill", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("email", LoginScreen.EMAIL);
                    hashMap.put("totalMoney", String.valueOf(totalMoney));
                    return hashMap;
                }
            };
            requestQueue.add(stringRequest);
        });


    }
}