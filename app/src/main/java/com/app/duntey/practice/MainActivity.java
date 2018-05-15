/* The comments in this code describe what I THINK is happening.
I have no idea if they are accurate
 */

package com.app.duntey.practice;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter HC05; //create a bluetooth object
    private Set<BluetoothDevice> pairedDevices; //idk what this is

    ListView lv; //create an object for list view



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HC05 = BluetoothAdapter.getDefaultAdapter(); //create an object that uses the default BT adapter on the device


        pairedDevices = HC05.getBondedDevices();
        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); //ask to turn on bluetooth
        //ask to turn on bluetooth
        startActivityForResult(turnOn, 0);

/*
        final TextView UpdatedText = findViewById(R.id.current_reading); //declare text object that refers to XML textobject ID
        final Button AllReadingsButton = findViewById(R.id.all_readings_button); //create a new button object for a button created in the XML file
        final Button StartButton = findViewById(R.id.start_button); */
        lv = findViewById(R.id.myList);


       /* AllReadingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HC05.getBondedDevices();
                //HC05.startDiscovery();
            }
        });*/
    }
    public void on(View v){
        if (!HC05.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
        }
    }

    public void off(View v){
        HC05.disable();
        Toast.makeText(getApplicationContext(), "Turned off" ,Toast.LENGTH_LONG).show();
    }
    public void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }


    public void list(View v){
        pairedDevices = HC05.getBondedDevices();

        ArrayList list = new ArrayList();

        for(BluetoothDevice bt : pairedDevices) list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);

        lv.setAdapter(adapter);
    }
/*
    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket
            // because mmServerSocket is final.
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code.
                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
            } catch (IOException e) {
                Log.e(TAG, "Socket's listen() method failed", e);
            }
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned.
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    Log.e(TAG, "Socket's accept() method failed", e);
                    break;
                }

                if (socket != null) {
                    // A connection was accepted. Perform work associated with
                    // the connection in a separate thread.
                    manageMyConnectedSocket(socket);
                    mmServerSocket.close();
                    break;
                }
            }
        }

        // Closes the connect socket and causes the thread to finish.
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the connect socket", e);
            }
        }*/
    }
}


//Pooooooooop
//poop2
//poop3


