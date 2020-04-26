package com.android.wifiradar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    EditText ip, port, user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip = (EditText)findViewById(R.id.ip_text);
        port = (EditText)findViewById(R.id.port_text);
        user = (EditText)findViewById(R.id.user_text);
        pass = (EditText)findViewById(R.id.pass_text);
    }


    public void goControls(View v){
        final String finalIp = ip.getText().toString();
        final String finalport = port.getText().toString();
        final String finalUser = user.getText().toString();
        final String finalpass = pass.getText().toString();



        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    if (executeRemoteCommand(finalUser, finalpass,finalIp, Integer.parseInt(finalport))){

                        FragControls fragControl = FragControls.newInstance(finalIp,finalport,finalUser,finalpass);
                        getSupportFragmentManager().beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .add(R.id.frame_controls, fragControl)
                                .commit();
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(1);
    }

    public static boolean executeRemoteCommand(String username,String password,String hostname,int port)
            throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, hostname, port);
        session.setPassword(password);

        // Avoid asking for key confirmation
        Properties prop = new Properties();
        prop.put("StrictHostKeyChecking", "no");
        session.setConfig(prop);

        session.connect();

        if (session.isConnected()){
            session.disconnect();
            return true;
        }else{
            return false;
        }
    }

}
