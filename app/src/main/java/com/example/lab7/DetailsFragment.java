package com.example.lab7;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
private Bundle passedData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instantiating a View object to be returned
        // I did this so I could instantiate views
        View result = inflater.inflate(R.layout.fragment_details, container, false);

        // getting bundle
        passedData = getArguments();


        // instantiating views
        // changing the textView content
        TextView nameTxt = (TextView)result.findViewById(R.id.nameVal);
        nameTxt.setText(passedData.getString("name"));

        TextView heightTxt = (TextView) result.findViewById(R.id.heightVal);
        heightTxt.setText(passedData.getString("height"));

        TextView massTxt = (TextView) result.findViewById(R.id.massVal);
        massTxt.setText(passedData.getString("mass"));

        return result;
    }
}