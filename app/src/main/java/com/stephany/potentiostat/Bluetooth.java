package com.stephany.potentiostat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class Bluetooth extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;
    private int count=1;

    private ImageView BTIcon;
    private TextView txtPairedDevices;

    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        TextView txtBTStatus = findViewById(R.id.txtBTStatus);
        txtPairedDevices =findViewById(R.id.txtPairedDevices);
        Button btnBTOn = findViewById(R.id.btnBTOn);
        Button btnBTOff = findViewById(R.id.btnBTOff);
        Button btnDiscoverable = findViewById(R.id.btnDiscoverable);
        Button btnPaired = findViewById(R.id.btnPaired);
        BTIcon = findViewById(R.id.BTIcon);

        //Adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //Check BT availability
        if(mBluetoothAdapter==null){
            txtBTStatus.setText(R.string.BT_not_avail);
        }else{
            txtBTStatus.setText(R.string.BT_avail);
        }

        //change image if BT available
        if(mBluetoothAdapter.isEnabled()){
            BTIcon.setImageResource(R.drawable.ic_bluetooth);
        }else{
            BTIcon.setImageResource(R.drawable.ic_bluetooth_off);
        }

        btnBTOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mBluetoothAdapter.isEnabled()){
                    //Intent to turn on BT
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, REQUEST_ENABLE_BT);
                }else{
                    showToast(getString(R.string.BT_alreadyOn));
                }
            }
        });

        btnBTOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBluetoothAdapter.isEnabled()){
                    mBluetoothAdapter.disable();
                    BTIcon.setImageResource(R.drawable.ic_bluetooth_off);
                    txtPairedDevices.setText("");
                    showToast(getString(R.string.BT_turnOff));
                }else{
                    showToast("Bluetooth is already off");
                }
            }
        });

        btnDiscoverable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mBluetoothAdapter.isDiscovering()){
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, REQUEST_DISCOVER_BT);
                    showToast("Making device discoverable");
                }

            }
        });

        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBluetoothAdapter.isEnabled()){
                    txtPairedDevices.setText(R.string.paired_devices_);
                    Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
                    count =1;
                    for(BluetoothDevice device: devices){
                        txtPairedDevices.append("\n" + count +"." + device.getName() + "," + device);
                        count++;
                    }
                }else{
                    showToast("Turn on bluetooth to get paired devices");
                }

            }
        });
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                BTIcon.setImageResource(R.drawable.ic_bluetooth);
                showToast("Bluetooth is on");
            } else {
                showToast("Failed to turn on bluetooth");
            }
        }
    }
}