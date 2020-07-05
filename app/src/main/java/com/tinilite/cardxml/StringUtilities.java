package com.tinilite.cardxml;

import android.util.Log;

public class StringUtilities {


    public static String byteToHex (byte pByte){
        String returnValue = Integer.toHexString(pByte);
        returnValue = returnValue.substring(returnValue.length() -2);

        return returnValue;
    }

    public static String byteToBin (byte pByte){
        String returnValue = "" ;

        short mask = 0b00000001;
        for (int i = 7; i >=0  ; i--)
        {
            if ( ((pByte >> i) & mask) == 1)
                returnValue = returnValue + "1";
            else
                returnValue = returnValue + "0";
        }
        return returnValue;
    }

    public static String padRight(String s, String pad, int n) {

        String returnValue = String.format("%-" + n + "s", s);
        if (!pad.equals(" ")) {
            returnValue = returnValue.replace(" ", pad);
        }

        if (returnValue.length() > n) {
            returnValue = returnValue.substring(0, n);
        }

        return returnValue;
    }

    public static String padLeft(String s, String pad, int n) {

        String returnValue = String.format("%" + n + "s", s);
        if (!pad.equals(" ")) {
            returnValue = returnValue.replace(" ", pad);
        }

        if (returnValue.length() > n) {
            returnValue = returnValue.substring(0, n);
        }

        return returnValue;
    }

    public static String join(String delim, String[] arr) {

        StringBuilder returnValueBld = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            returnValueBld.append(arr[i]);
            if (i < arr.length - 1) {
                returnValueBld.append(delim);
            }
        }

        return returnValueBld.toString();
    }
}
