package org.example.example2.v2;

public class Buffer2 {
    private int data;
    private boolean hasData = false;

    public int getData() {
        return data;
    }

    public void setData(int value) {
        this.data = value;
    }

    public boolean hasData() {
        return hasData;
    }

    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }
}