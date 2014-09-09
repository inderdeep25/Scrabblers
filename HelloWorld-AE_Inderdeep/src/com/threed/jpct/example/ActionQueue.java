package com.threed.jpct.example;

import java.util.ArrayList;
import java.util.List;

public class ActionQueue {
    private List<IAction> _payload;

    public ActionQueue (){
        _payload = new ArrayList<IAction>();
    }

    public void AddToQueue(IAction action){
        _payload.add(action);
    }

    public void Fire(IMessage m){
        for(IAction action : _payload){
            action.Invoke(m);
        }
    }
}
