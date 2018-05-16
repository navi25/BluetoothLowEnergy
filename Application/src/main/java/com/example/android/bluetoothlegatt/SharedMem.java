package com.example.android.bluetoothlegatt;

import android.content.Context;
import android.util.Log;

import com.newtronlabs.sharedmemory.IRemoteSharedMemory;
import com.newtronlabs.sharedmemory.RemoteMemoryAdapter;
import com.newtronlabs.sharedmemory.SharedMemoryProducer;
import com.newtronlabs.sharedmemory.prod.memory.ISharedMemory;

import java.io.IOException;

public class SharedMem {
    static ISharedMemory sharedMemory = null;
    static int sizeInBytes = 2*(1024*1024);
    static String regionName = "health-reg";
    static IRemoteSharedMemory remoteMemory = null;

    static String TAG = SharedMem.class.getSimpleName();

    static String producerAppId = "com.example.android.bluetoothlegatt";

    static ISharedMemory getSharedMemory() throws IOException{
        if(sharedMemory == null){
            sharedMemory = SharedMemoryProducer.getInstance().allocate(regionName, sizeInBytes);
//
        }
        return sharedMemory;
    }

    public static void writeToSharedMem(byte[] data){
        try{
            writeData(data);
            Log.d(TAG,"Wrote Data in SharedMemory");
        }
        catch (IOException e){
            Log.d(TAG,e.toString());
        }
    }

    static void writeData(byte[] data) throws IOException{
        getSharedMemory().writeBytes(data, 0, 0, data.length);
    }

    static IRemoteSharedMemory getRemoteSharedMemory(Context context){
        if(remoteMemory == null){
            remoteMemory = RemoteMemoryAdapter.getDefaultAdapter().getSharedMemory(context, producerAppId, regionName);
        }
        return remoteMemory;
    }


    public static byte[] readFromSharedMem(Context context){
        IRemoteSharedMemory remoteSharedMemory = getRemoteSharedMemory(context);
        byte[] data = new byte[remoteSharedMemory.getSize()];
        Log.d(TAG,"the data is " + new String(data));
        return data;
    }
}
