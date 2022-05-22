package com.group7.appsellclothes.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.group7.appsellclothes.R;
import com.group7.appsellclothes.model.User;


public class PersonalInformationFragment extends Fragment {

    public static final String TAG =PersonalInformationFragment.class.getName();
    TextView txtNamePersonal;
    ImageView imgBackPersonal;
    User user ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_personal_information, container, false);
        user = (User) getArguments().getSerializable("user");
        addControl(view);
        addEvent();


        txtNamePersonal.setText(user.getUsername());
        return view;
    }

    private void addEvent() {
        imgBackPersonal.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(getFragmentManager()!=null){
                getFragmentManager().popBackStack();

            }
        }
    });
    }

    private void addControl(View view) {
        txtNamePersonal = view.findViewById(R.id.txt_user_name_personal) ;
        imgBackPersonal = view.findViewById(R.id.img_back_personal);
    }

}