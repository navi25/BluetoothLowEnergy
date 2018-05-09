package com.example.android.bluetoothlegatt;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class CSVReader {

    private  static String defaultFileName = "/health.txt";
    private final static String headers = "id , index , deviceId , data , deviceId";
    private static String rowDataStringFormat = "%s, %s , %s, %s\n";
    static String TAG = "CSVReader";
    static File file = null;

    public static String getFormattedData(byte[] rawData){
        return new String(rawData);
//        StringBuffer sb = new StringBuffer("");
//        for(int i=0; i<rawData.length; i++){
//            int curr = rawData[i];
//            sb.append(curr);
//        }
//
//        return sb.toString();
    }

    public static void write(byte[] rawData, File path){
        String data = getFormattedData(rawData);
        Date currentTime = Calendar.getInstance().getTime();
        String dateValue = currentTime.toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String epochTime = Long.toString(timestamp.getTime());
        String uid = UUID.randomUUID().toString();
        String deviceId = "IrSensor2";

        String row = String.format(rowDataStringFormat,epochTime,deviceId,data,dateValue);
        Log.d(TAG,"row value - " + row);
        writeToFile(row,path);

    }

    public static void write(String data,File path){
        Date currentTime = Calendar.getInstance().getTime();
        String dateValue = currentTime.toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String epochTime = Long.toString(timestamp.getTime());
        String uid = UUID.randomUUID().toString();
        String deviceId = "IrSensor2";

        String row = String.format(rowDataStringFormat,uid,epochTime,deviceId,data,dateValue);
        Log.d(TAG,"row value - " + row);
        writeToFile(row,path);
    }

    static void writeToFile(String row, File path){
        Log.d(TAG,"absolute storage path" + path.getAbsolutePath());
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        File file = getFile(path);
        try{
            fw = new FileWriter(file, true);

            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.write(row);

            out.close();
        } catch (IOException e) {
            Log.d(TAG,"1" + e.toString());
        }
        finally {
            try {
                if(out != null)
                    out.close();
            } catch (Exception e) {
                Log.d(TAG,"2" +e.toString());
            }
            try {
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                Log.d(TAG,"3" +e.toString());
            }
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                Log.d(TAG,"4" +e.toString());
            }
        }

    }



    static File getFile(File path){
        if(file == null){
          file = new File(path,defaultFileName);
        }
        return file;
    }




}
