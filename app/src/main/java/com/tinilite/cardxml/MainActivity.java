package com.tinilite.cardxml;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements CardFragment.OnCardFragmentListener
{

    private static final String TAG = "MainActivity";
    private static final String TAG1 = "MainActivity";
    private static final String TAG2 = "MainActivity";

    byte[] mSpiCode ;
    Button mButSubmitt;
    TextView [] mSegments = new TextView[17];
    EditText mEtLetter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: begin");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtLetter = findViewById(R.id.etLetter);
        mButSubmitt = findViewById(R.id.butSubmit);


        mButSubmitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG1, "onClick: begin");
             String letter = mEtLetter.getText().toString();
                Log.d(TAG1, "onClick: letter from mEtLetter: '" + letter + "'" );
             if (letter.length() == 0) return;
             letter = letter.substring(0,1);
              //byte[] spicode = Spi.generateSpiCode(letter);
                mSpiCode = Spi.generateSpiCode(letter);

            }
        });
    }

    @Override
    public byte[] OnPollBytes() {

        return mSpiCode;
    }
}