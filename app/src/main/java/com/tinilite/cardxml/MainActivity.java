package com.tinilite.cardxml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String TAG1 = "MainActivity";
    private static final String TAG2 = "MainActivity";

    Button mButSubmitt;
    TextView [] mSegments = new TextView[16];
    EditText mEtLetter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: begin");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtLetter = findViewById(R.id.etLetter);
        mButSubmitt = findViewById(R.id.butSubmit);
        mSegments[0] = findViewById(R.id.segment1);
        mSegments[1] = findViewById(R.id.segment2);
        mSegments[2] = findViewById(R.id.segment3);
        mSegments[3] = findViewById(R.id.segment4);
        mSegments[4] = findViewById(R.id.segment5);
        mSegments[5] = findViewById(R.id.segment6);
        mSegments[6] = findViewById(R.id.segment7);
        mSegments[7] = findViewById(R.id.segment8);
        mSegments[8] = findViewById(R.id.segment9);
        mSegments[9] = findViewById(R.id.segment10);
        mSegments[10] = findViewById(R.id.segment11);
        mSegments[11] = findViewById(R.id.segment12);
        mSegments[12] = findViewById(R.id.segment13);
        mSegments[13] = findViewById(R.id.segment14);
        mSegments[14] = findViewById(R.id.segment15);
        mSegments[15] = findViewById(R.id.segment16);

        mButSubmitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG1, "onClick: begin");
             String letter = mEtLetter.getText().toString();
                Log.d(TAG1, "onClick: letter from mEtLetter: '" + letter + "'" );
             if (letter.length() == 0) return;
             letter = letter.substring(0,1);
              byte[] spicode = Spi.generateSpiCode(letter);
                Log.d(TAG1, "onClick: spicode length " + spicode.length );
              for (int i = 0 ; i < spicode.length; i++){
                  Log.d(TAG1, "onClick: spicode["  + i + "] = " + spicode[i] );
              }

              String hexForm = Spi.spicodeToHex(spicode);
                Log.d(TAG1, "onClick: hexfor, " + hexForm );

                //Toast.makeText(getApplicationContext(), Spi.spicodeToHex(spicode), Toast.LENGTH_LONG).show();
             String binaryform = Spi.spicodeToBin(spicode);
                     //StringUtilities.padLeft (Integer.toUnsignedString  (spicode[0], 2) , "0" , 8)  +
                     //StringUtilities.padLeft (Integer.toUnsignedString  (spicode[1],2), "0", 8 );
                Log.d(TAG1, "onClick: binaryform = " + binaryform );
             for (int i = 0;i < 16; i++){

                 String thisBit = binaryform.substring(i,i+1);
                 Log.d(TAG1, "onClick: bit " + i + " = " + thisBit );
                 if (thisBit.equals("1") )
                     mSegments[i].setBackground(getDrawable( R.drawable.segment_on));
                 else
                     mSegments[i].setBackground(getDrawable( R.drawable.segment_off));
             }
            }
        });
    }
}