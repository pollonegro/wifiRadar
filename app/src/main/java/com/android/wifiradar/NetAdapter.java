package com.android.wifiradar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NetAdapter extends RecyclerView.Adapter<NetAdapter.ViewHolder>{

    private ArrayList<WifiTarget> wifis;

    public NetAdapter(ArrayList<WifiTarget> wifis) {
        this.wifis = wifis;

    }


    //En un adaptador es obligatorio definir una clase que herede de RecyclerView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        //La clase ViewHolder hará referencia a los elementos de la vista creada para el recycler view
        public TextView name;
        public TextView id;
        public TextView strenght;

        //Su constructor debera enlazar las variables del controlador con la vista
        public ViewHolder(final View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.wifi_name);
            this.id = (TextView) itemView.findViewById(R.id.wifi_id);
            this.strenght = (TextView) itemView.findViewById(R.id.wifi_str);

        }
    }

    @Override
    public NetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Especificamos el fichero XML que se utilizará como vista
        View contactView = inflater.inflate(R.layout.adapter_wifis, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(NetAdapter.ViewHolder holder, final int position) {
        final  WifiTarget wifi = this.wifis.get(position);
        holder.name.setText(wifi.getName());
        holder.id.setText(wifi.getId());
        holder.strenght.setText(wifi.getStrenght());

    }



    @Override
    public int getItemCount() {
        return wifis.size();
    }
}