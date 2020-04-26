package com.android.wifiradar;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Properties;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragControls#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragControls extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IP = "ip";
    private static final String ARG_PORT = "port";
    private static final String ARG_USER = "user";
    private static final String ARG_PASS = "pass";

    private BufferedReader in;
    // TODO: Rename and change types of parameters
    private String ip;
    private String port;
    private String user;
    private String pass;

    private RecyclerView recyclerNets;
    private NetAdapter adapterNets;
    private ArrayList<WifiTarget> wifis;
    private TextView horizontalProgress;

    private Button up, down, left, right;

    private Session mySession;

    public void setMySession(Session ses){
        mySession=ses;
    }

    public FragControls() {
        // Required empty public constructor
    }

    public static FragControls newInstance(String ip, String port, String user, String pass) {
        FragControls fragment = new FragControls();
        Bundle args = new Bundle();
        args.putString(ARG_IP, ip);
        args.putString(ARG_PORT, port);
        args.putString(ARG_USER, user);
        args.putString(ARG_PASS, pass);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            ip = getArguments().getString(ARG_IP);
            port = getArguments().getString(ARG_PORT);
            user = getArguments().getString(ARG_USER);
            pass = getArguments().getString(ARG_PASS);
        }
        wifis = new ArrayList<>();
        wifis.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_controls, container, false);
        recyclerNets = (RecyclerView) v.findViewById(R.id.recyler_nets);
        recyclerNets.setLayoutManager(new GridLayoutManager(getActivity(),1));
        adapterNets = new NetAdapter(wifis);
        recyclerNets.setAdapter(adapterNets);


        InputStream inputStream = getResources().openRawResource(R.raw.redes);
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
        String eachline = null;
        try {
            eachline = bufferedReader.readLine();
            while (eachline != null) {
                // `the words in the file are separated by space`, so to get each words
                String[] words = eachline.split("\\s+");
                WifiTarget newWifi = new WifiTarget(words[0],words[2],words[1]);
                wifis.add(newWifi);
                adapterNets.notifyItemInserted(wifis.size()-1);
                eachline = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




        // set a change listener on the SeekBar
        SeekBar seekBar = v.findViewById(R.id.seekBarHorizontal);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        int angle = seekBar.getProgress();
        horizontalProgress = v.findViewById(R.id.textView);
        horizontalProgress.setText("Horizontal angle: " + angle);

        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {

                    centerAntenna();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(1);

        Toast.makeText(getContext(), "Initialization completed", Toast.LENGTH_SHORT).show();
        return v;
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, final int angle, boolean fromUser) {
            // updated continuously as the user slides the thumb
            horizontalProgress.setText("Horizontal angle: " + angle);


            new  AsyncTask<Integer, Void, Void>(){
                @Override
                protected Void doInBackground(Integer... params) {
                    try {
                        moveHorizontalAngle(angle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute(1);


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

    public void onPadPress(View v){
        switch (v.getId()){
            case R.id.butUp:
                break;
            case R.id.buttonDown:
                break;
            case R.id.buttonLeft:
                break;
            case R.id.buttonRight:
                break;
            default:
                break;
        }
    }



    public void centerAntenna()
            throws Exception {

        JSch jsch = new JSch();
        Session session = jsch.getSession(user,ip, Integer.parseInt(port));
        session.setPassword(pass);

        // Avoid asking for key confirmation
        Properties prop = new Properties();
        prop.put("StrictHostKeyChecking", "no");
        session.setConfig(prop);

        session.connect();

        // SSH Channel
        ChannelExec channelssh = (ChannelExec)
                session.openChannel("exec");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        channelssh.setOutputStream(baos);

        // Execute command
        channelssh.setCommand("python3 /root/SCRIPTS/start.py");
        channelssh.connect();
        channelssh.disconnect();
    }


    public void moveHorizontalAngle(int angle)
            throws Exception {


        JSch jsch = new JSch();
        Session session = jsch.getSession(user,ip, Integer.parseInt(port));
        session.setPassword(pass);

        // Avoid asking for key confirmation
        Properties prop = new Properties();
        prop.put("StrictHostKeyChecking", "no");
        session.setConfig(prop);

        session.connect();

        // SSH Channel
        ChannelExec channelssh = (ChannelExec)
                session.openChannel("exec");

        in = new BufferedReader(new InputStreamReader(channelssh.getInputStream()));

        // Execute command
//        channelssh.setCommand("python3");
//        channelssh.setCommand("import threading; from tkinter import *;from pynput import keyboard;import time, os;from adafruit_servokit import ServoKit;kit = ServoKit(channels=16); kit.servo[1].angle = "+angle);
        String lastAngle = String.valueOf(angle);
        DataInputStream dataIn = new DataInputStream(channelssh.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(channelssh.getOutputStream());
        BufferedReader bufferReader= new BufferedReader(new InputStreamReader(dataIn));

//        channelssh.setCommand("python3 /root/SCRIPTS/sshReceiver.py 22");
        dataOut.writeBytes("python3 /root/SCRIPTS/sshReceiver.py 22");


        System.out.println("_______________");
        channelssh.connect();
        channelssh.disconnect();
    }
}
