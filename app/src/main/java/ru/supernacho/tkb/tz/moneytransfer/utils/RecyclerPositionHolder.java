package ru.supernacho.tkb.tz.moneytransfer.utils;

import java.util.Stack;

public class RecyclerPositionHolder {
    private Stack<Integer> positionHolder = new Stack<>();

    public void setPosition(int pos){
        positionHolder.push(pos);
    }

    public void doCorrection(int pos){
        if (positionHolder.peek() == pos) positionHolder.pop();
    }

    public int getActualPosition(){
        return positionHolder.peek();
    }
}
