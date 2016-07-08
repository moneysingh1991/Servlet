package com.company.servlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.button;

public class DoubleMeActivity extends Activity implements OnClickListener {

    EditText inputValue=null;
    Integer doubledValue =0;
    Button doubleMe;
    Button get_coardinate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = (EditText) findViewById(R.id.inputNum);
        doubleMe = (Button) findViewById(R.id.doubleme);
       // get_coardinate = (Button) findViewById(R.id.button_cordinate);

        doubleMe.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.doubleme:

                new Thread(new Runnable() {
                    public void run() {

                        try{
                            URL url = new URL("http://98.248.201.20:8080/web_server/double");
                            URLConnection connection = url.openConnection();

                            String inputString = inputValue.getText().toString();
                            //inputString = URLEncoder.encode(inputString, "UTF-8");

                            Log.d("inputString", inputString);

                            connection.setDoOutput(true);
                            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                            out.write(inputString);
                            out.close();

                            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                            String returnString="";
                            doubledValue =0;

                            while ((returnString = in.readLine()) != null)
                            {
                                doubledValue= Integer.parseInt(returnString);
                            }
                            in.close();


                            runOnUiThread(new Runnable() {
                                public void run() {

                                    inputValue.setText(doubledValue.toString());

                                }
                            });

                        }catch(Exception e)
                        {
                            Log.d("Exception",e.toString());
                        }

                    }
                }).start();

                break;


        }
    }


    public void coardinate_layout(View view) {
        //setContentView(R.layout.activity_location);

        Intent intent = new Intent(this, Coardinate.class);

        startActivity(intent);
    }
}