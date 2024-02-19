package com.example.androidtask_onedata.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtask_onedata.R;
import com.example.androidtask_onedata.model.BtModel;

import java.util.ArrayList;

public class BtAdapter extends RecyclerView.Adapter<BtAdapter.MyViewHolder> {


    private ArrayList<BtModel> devices = new ArrayList<>();

    public BtAdapter(ArrayList<BtModel> devices) {
        this.devices = devices;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bt_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(devices.get(position).name);

    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.itemTextView);
        }
    }
}
