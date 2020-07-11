package com.tinilite.cardxml;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CardFragment extends Fragment {
    private static final String TAG = "CardFragment";
    byte[] mSpiCode ;
    TextView[] mSegments = new TextView[17];

    OnCardFragmentListener mListener;

    interface OnCardFragmentListener {
        byte[] OnPollBytes();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCardFragmentListener) {
            mListener = (OnCardFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + "must implement OnCardFragmentListener");
        }

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            byte[] fSpiCode;
                            fSpiCode = mListener.OnPollBytes();
                            if (fSpiCode != null  &&
                                fSpiCode.length == 2 &&
                                (
                                        mSpiCode == null ||
                                        mSpiCode.length != 2 ||
                                        fSpiCode[0] != mSpiCode[0] ||
                                            fSpiCode[1] != mSpiCode[1]
                                )

                            ) {

                                mSpiCode = fSpiCode;
                                //update display
                                //
                                Log.d(TAG, "onClick: spicode length " + fSpiCode.length );
                                for (int i = 0 ; i < fSpiCode.length; i++){
                                    Log.d(TAG, "onClick: spicode["  + i + "] = " + fSpiCode[i] );
                                }

                                String hexForm = Spi.spicodeToHex(fSpiCode);
                                Log.d(TAG, "onClick: hexfor, " + hexForm );

                                //Toast.makeText(getApplicationContext(), Spi.spicodeToHex(spicode), Toast.LENGTH_LONG).show();
                                String binaryform = Spi.spicodeToBin(fSpiCode);
                                //StringUtilities.padLeft (Integer.toUnsignedString  (spicode[0], 2) , "0" , 8)  +
                                //StringUtilities.padLeft (Integer.toUnsignedString  (spicode[1],2), "0", 8 );
                                Log.d(TAG, "onClick: binaryform = " + binaryform );

                                for (int i = 1;i <= 16; i++){

                                    int segmentno = i;

                                    String thisBit = binaryform.substring(i - 1, i );
                                    Log.d(TAG, "onClick: bit " + i + " = " + thisBit + " segment " + segmentno);
                                    if (thisBit.equals("1"))
                                        mSegments[segmentno].setBackground(getContext().getDrawable(R.drawable.segment_off));
                                    else
                                        mSegments[segmentno].setBackground(getContext().getDrawable(R.drawable.segment_on));

                                }
                            }

                        }
                    });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }

    public CardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View view = inflater.inflate(R.layout.fragment_card, container, false);
        mSegments[1] = view.findViewById(R.id.segment1);
        mSegments[2] = view.findViewById(R.id.segment2);
        mSegments[3] = view.findViewById(R.id.segment3);
        mSegments[4] = view.findViewById(R.id.segment4);
        mSegments[5] = view.findViewById(R.id.segment5);
        mSegments[6] = view.findViewById(R.id.segment6);
        mSegments[7] = view.findViewById(R.id.segment7);
        mSegments[8] = view.findViewById(R.id.segment8);
        mSegments[9] = view.findViewById(R.id.segment9);
        mSegments[10] = view.findViewById(R.id.segment10);
        mSegments[11] = view.findViewById(R.id.segment11);
        mSegments[12] = view.findViewById(R.id.segment12);
        mSegments[13] = view.findViewById(R.id.segment13);
        mSegments[14] = view.findViewById(R.id.segment14);
        mSegments[15] = view.findViewById(R.id.segment15);
        mSegments[16] = view.findViewById(R.id.segment16);


        return view;
    }
}


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CardFragment.
     */
    // TODO: Rename and change types and number of parameters
    /*
     // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @org.jetbrains.annotations.NotNull
    public static CardFragment newInstance(String param1, String param2) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    */





