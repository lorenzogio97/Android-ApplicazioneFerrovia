package com.example.traininfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class LoginFrag extends Fragment {

    public View viewF;
    private LoginManager loginManager;
    private static TextView usertxt;
    private static TextView passtxt;
    private static Button btn_login;

    public LoginFrag() {
        //costruttore vuoto richiesto
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewF = inflater.inflate(R.layout.fragment_login, container, false);
        usertxt= viewF.findViewById(R.id.username);
        passtxt= viewF.findViewById(R.id.password);
        btn_login=viewF.findViewById(R.id.btn_login);
        loginManager= new LoginManager(getContext());

        //listener per il bottone di login
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loginManager.isSet()) {
                    loginManager.deleteCredential();
                } else {
                    loginManager.writeCredentials(usertxt.getText().toString(), passtxt.getText().toString());
                }

            }
        });

        if(loginManager.isSet()) {
            interface_logout();
        } else {
            interface_login();
        }

        return viewF;
    }

    static void interface_login () {
        usertxt.setVisibility(View.VISIBLE);
        passtxt.setVisibility(View.VISIBLE);
        btn_login.setText(R.string.button_login);
    }

    static void interface_logout () {
        usertxt.setVisibility(View.INVISIBLE);
        usertxt.setText(null);
        passtxt.setVisibility(View.INVISIBLE);
        passtxt.setText(null);
        btn_login.setText(R.string.button_logout);
    }
}