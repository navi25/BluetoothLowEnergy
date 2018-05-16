package com.example.android.bluetoothlegatt;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class QueueMemory {


    static Queue<byte[]> memory = null;

    static Queue<byte[]> getMemory(){
        if(memory == null){
            memory = new LinkedList<byte[]>();
        }
        return memory;
    }

    public static void write(byte[] data){
        getMemory().add(data);
    }

    public static byte[] read(){
        return getMemory().poll();
    }
}
