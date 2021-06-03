package com.example.project_thuc_tap;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.project_thuc_tap.adapter.ProductAdapter;
import com.example.project_thuc_tap.model.Product;
import com.example.project_thuc_tap.ulities.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LayoutInflater inflater;
    ViewFlipper viewFlipper;
    ArrayList<Product> products;
    ProductAdapter productAdapter = new ProductAdapter(getContext(), products);
    RecyclerView recyclerViewHome;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TabHome() {
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
    public static TabHome newInstance(String param1, String param2) {
        Log.d("TabHome", "newInstance");
        TabHome fragment = new TabHome();
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
        View view = inflater.inflate(R.layout.fragment_tab_home, container, false);
        /*
         Inflate the layout for this fragment
        Map
        */
        Log.d("TabHome", "onCreateView");
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
        products = new ArrayList<>();
        productAdapter = new ProductAdapter(getContext(), products);
        recyclerViewHome = (RecyclerView) view.findViewById(R.id.recycleViewProduct);
        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerViewHome.setAdapter(productAdapter);
        //MapEnd
        ActionViewFlipper();
        GetDataNewProduct();

        return view;
    }

    private void GetDataNewProduct() {
        Log.d("TabHome", "GetDataNewProduct");
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.urlProductNew, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("TabHome", "response: " + response);
                if(response != null){
                    int ID = 0;
                    String TenSP = "";
                    Integer GiaSp = 0, XepLoai = 0;
                    String HinhAnhSp = "";
                    String MotaSP = "";
                    int IDLoaiSp = 0;

                    for(int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            TenSP = jsonObject.getString("tensp");
                            GiaSp = jsonObject.getInt("giasp");
                            HinhAnhSp = jsonObject.getString("hinhanhSP");
                            MotaSP = jsonObject.getString("mota");
                            IDLoaiSp = jsonObject.getInt("idLoai");

                            //int ID, String tenSP, Integer giaSp, String hinhAnhSp, String motaSP, int IDloaiSP, int rate
                            products.add(new Product(ID, TenSP, GiaSp, HinhAnhSp, MotaSP, IDLoaiSp, XepLoai));
                            //cập nhật lại giao diện
                            productAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TabHome", "response: " + error);
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {

        //view flipper để chạy quảng cáo tĩnh
        ArrayList<String> arrayAD = new ArrayList<>();
        arrayAD.add("https://www.feasbo.com/zykyrdoa/2020/08/iherb-promo-code.jpg");
        arrayAD.add("https://1.bp.blogspot.com/-T75lW-NGdUU/XMTZygIPppI/AAAAAAAAJ-8/fEOUDhL3qOIlPNn3GGvvsOX7uqHo12o6ACLcBGAs/s1600/Iherb%2BFavourites.png");
        arrayAD.add("https://play-lh.googleusercontent.com/gU4ixig4k1zA831Usg-tCFwht4gqjbkvevEQbNqSTWD4Xeenr__ONeOCyLv-FrfHFAGa");
        for(int i = 0; i<arrayAD.size(); i++){
            ImageView imageView = new ImageView(getActivity());
            Picasso.with(getActivity()).load(arrayAD.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(v -> {
                Toast.makeText(getActivity(), "dday la buc anh",Toast.LENGTH_LONG).show();
            });
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    void Map(){

    }


    //TODO



}