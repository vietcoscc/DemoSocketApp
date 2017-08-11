package com.example.viet.demosocketapp;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by viet on 08/08/2017.
 */

public class ClientAsyncTask extends AsyncTask<String, Void, Void> {
    public static final String IP = "192.168.0.150";
    public static final int PORT = 8979;

    @Override
    protected Void doInBackground(String... strings) {
        try {
            Socket socket = new Socket(IP, PORT);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            outputStream.write('a');
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
