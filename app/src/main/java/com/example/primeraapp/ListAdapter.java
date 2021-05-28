package com.example.primeraapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<listElement> mdata;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<listElement> item, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mdata = item;
    }
    @Override
    public int getItemCount(){
        return mdata.size();
    }
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mdata.get(position));
    }

    public void setItems(List<listElement>items){
        mdata = items;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView color, ejercicio, descripcion;

        ViewHolder(View view){
            super(view);
            iconImage = view.findViewById(R.id.iconImageView);
            ejercicio = view.findViewById(R.id.nameTxtV);
            descripcion = view.findViewById(R.id.decripcionTv);
        }

        void bindData(final listElement item) {
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            ejercicio.setText(item.getEjercicio());
            descripcion.setText(item.getDescripcion());
        }
    }
}
