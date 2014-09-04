package com.asherLaakes.cavemanRunner.Messaging;

import android.util.Pair;

import com.threed.jpct.Logger;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Messager {
    Map<Type, ActionQueue> queueMap;

    public Messager(){
        queueMap = new HashMap<Type, ActionQueue>();
    }

    public void Publish(IMessage data){
        if(queueMap.containsKey(data.getClass()))
            queueMap.get(data.getClass()).Fire(data);
        else {
            Logger.log("message type " + data.getClass().getCanonicalName() + " not found!");
            for(Type key: queueMap.keySet())
                Logger.log("type " + key.getClass().getCanonicalName() + " found");
        }
    }

    public void Subscribe(Type type, IAction action) {
        if (queueMap.containsKey(type))
            queueMap.get(type).AddToQueue(action);
        else{
            queueMap.put(type, new ActionQueue());
            ActionQueue target = queueMap.get(type);
            target.AddToQueue(action);
        }
    }
}
