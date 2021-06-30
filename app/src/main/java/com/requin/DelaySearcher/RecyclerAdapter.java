package com.requin.DelaySearcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private OnRecyclerListener listener;
    private RailRouteEntity[] entity;

    public RecyclerAdapter(Context content, RailRouteEntity[] entity , OnRecyclerListener listener){
        this.inflater = LayoutInflater.from(content);
        this.entity = entity;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapter.ViewHolder holder, int position) {
        if(entity != null){
            holder.companyView.setText(entity[position].getCompany());
            holder.railView.setText(entity[position].getName());
            holder.sourceView.setText(entity[position].getSource());
        }

        holder.itemView.setOnClickListener(v -> listener.onRecyclerClicked(v, position));

    }

    @Override
    public int getItemCount() {
        if(entity != null){
            return entity.length;
        }else{
            return 0;
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView companyView;
        TextView railView;
        TextView sourceView;
        public ViewHolder(View itemView){
            super(itemView);
            companyView = (TextView)itemView.findViewById(R.id.company);
            railView = (TextView)itemView.findViewById(R.id.railName);
            sourceView = (TextView)itemView.findViewById(R.id.source);
        }

    }

}
