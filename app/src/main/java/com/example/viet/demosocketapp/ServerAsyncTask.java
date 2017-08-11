package com.example.viet.demosocketapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by viet on 08/08/2017.
 */

public class ServerAsyncTask extends AsyncTask<Integer, Integer, Socket> {
    public static final int PORT = 8979;

    @Override
    protected Socket doInBackground(Integer... integers) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Log.e("ServerAsyncTask", "...............");
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Socket socket) {
        super.onPostExecute(socket);
        if (onStartSuccess != null) {
            onStartSuccess.onSuccess(socket);
        }
    }

    private OnStartSuccess onStartSuccess;

    public void setOnStartSuccess(OnStartSuccess onStartSuccess) {
        this.onStartSuccess = onStartSuccess;
    }

    public interface OnStartSuccess {
        void onSuccess(Socket socket);
    }
}
