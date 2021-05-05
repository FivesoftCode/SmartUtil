package com.fivesoft.smartutil;

import java.util.ArrayList;

public class Average {

    ArrayList<Float> values;

    public Average(){
        values = new ArrayList<>();
    }

    public void addValue(float value){
        values.add(value);
    }

    public void addValue(int value){
        values.add((float) value);
    }

    public void addValue(double value){
        values.add((float) value);
    }

    public void addValue(long value){
        values.add((float) value);
    }

    public void addValue(short value){
        values.add((float) value);
    }

    public float average(){
        float sum = 0;

        for(Float f: values){
            sum += f;
        }
        return sum / Math.max(1, values.size());
    }

    public float sum(){
        float sum = 0;

        for(Float f: values){
            sum += f;
        }
        return sum;
    }

    public int count(){
        return values.size();
    }

    public void clear(){
        values.clear();
    }


}
