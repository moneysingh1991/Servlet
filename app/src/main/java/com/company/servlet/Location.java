package com.company.servlet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Button button = (Button) findViewById(R.id.button_location);

        final TextView textView = (TextView) findViewById(R.id.textView_location);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("This is location service");

                textView.setText("location");
            }
        });
    }


}
