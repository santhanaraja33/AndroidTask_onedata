package com.example.androidtask_onedata.screens;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtask_onedata.R;
import com.example.androidtask_onedata.adapters.BtAdapter;
import com.example.androidtask_onedata.model.BtModel;

import java.util.ArrayList;
import java.util.Set;


public class BluetoothFragment extends Fragment {

    private TextView tvSwitchText;
    private SwitchCompat btnSwitch;
    private CardView cvList;
    private RecyclerView rvList;
    private BtAdapter adapter;
    private ArrayList<BtModel> list = new ArrayList<>();
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View bluetooth = inflater.inflate(R.layout.fragment_bluetooth, container, false);


        tvSwitchText = bluetooth.findViewById(R.id.tvswitchText);
        btnSwitch = bluetooth.findViewById(R.id.btnSwitch);
        cvList = bluetooth.findViewById(R.id.cvList);
        rvList = bluetooth.findViewById(R.id.devicelist);


        adapter = new BtAdapter(list);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(adapter);


        btnSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {

                if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
                        return;
                    }
                }
                bluetoothManager = (BluetoothManager) requireActivity().getSystemService(Context.BLUETOOTH_SERVICE);
                if (Build.VERSION.SDK_INT >= 31) {
                    bluetoothAdapter = bluetoothManager.getAdapter();
                } else {
                    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                }
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

                if (pairedDevices.size() > 0) {
                    list.clear();
                    for (BluetoothDevice device : pairedDevices) {
                        BtModel bluetooth1 = new BtModel();
                        bluetooth1.name = device.getName();
                        list.add(bluetooth1);
                    }
//                    adapter.notify();
                }
                //check self permisson
                cvList.setVisibility(View.VISIBLE);
                tvSwitchText.setText("Show List");
            } else {

                tvSwitchText.setText("Hide List");
                cvList.setVisibility(View.GONE);
            }


        });

        return bluetooth;
    }


}