package com.android.wifiradar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FragNets extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerNets;
    private NetAdapter adapterNets;
    private ArrayList<WifiTarget> wifis;

    public FragNets() {
        // Required empty public constructor
    }

    public static FragNets newInstance(String param1, String param2) {
        FragNets fragment = new FragNets();
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

        wifis = new ArrayList<>();
        wifis.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_nets, container, false);

        recyclerNets = (RecyclerView) v.findViewById(R.id.recyler_nets);
        recyclerNets.setLayoutManager(new GridLayoutManager(getActivity(),1));
        adapterNets = new NetAdapter(wifis);
        recyclerNets.setAdapter(adapterNets);

        InputStream inputStream = getResources().openRawResource(R.raw.redes);
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
        String eachline = null;
        try {
            eachline = bufferedReader.readLine();
            while (eachline != null) {
                // `the words in the file are separated by space`, so to get each words
                String[] words = eachline.split("\\s+");
                WifiTarget newWifi = new WifiTarget(words[0],words[2],words[1]);
                wifis.add(newWifi);
                adapterNets.notifyItemInserted(wifis.size()-1);
                eachline = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }






        return v;
    }
}
