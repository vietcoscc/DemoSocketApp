package com.example.viet.demosocketapp;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String SERVICE_TYPE = "_http._tcp.";
    private static final String TAG = "MainActivity";
    @BindView(R.id.edtPort)
    EditText edtPort;
    @BindView(R.id.btnStartServer)
    Button btnStartServer;
    @BindView(R.id.edtContent)
    EditText edtContent;
    @BindView(R.id.btnSend)
    Button btnSend;
    private boolean isSarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        btnStartServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        ServerSocket serverSocket;
                        try {
                            Log.i(TAG, "Open port");
                            serverSocket = new ServerSocket(3222);

//                            String ip = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
//                            Log.i(TAG, ip);
                            Log.i(TAG, serverSocket.getInetAddress().getHostName());
                            while (true) {
                                Log.i(TAG, "Waiting connect");
                                Socket socket = serverSocket.accept();
                                Log.i(TAG, "Client connected");
                                InputStream inputStream = socket.getInputStream();
                                OutputStream outputStream = socket.getOutputStream();
                                Log.i(TAG, inputStream.read() + " ");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Log.i(TAG, "Connect ... ");
                            Socket socket = new Socket("localhost", 3222);
                            Log.i(TAG, socket.getInetAddress().getHostAddress());
                            Log.i(TAG, socket.isConnected() + " ");
                            InputStream inputStream = socket.getInputStream();
                            OutputStream outputStream = socket.getOutputStream();
                            outputStream.write('A');
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });
    }
}
