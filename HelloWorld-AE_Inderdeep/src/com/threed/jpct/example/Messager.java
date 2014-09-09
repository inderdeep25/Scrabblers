package com.threed.jpct.example;

import com.threed.jpct.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Messager {
    private static Messager _instance = null;

    public static Messager GetInstance(){
        if(_instance == null)
            _instance = new Messager();

        return _instance;
    }

    private Map<Type, ActionQueue> _actionMap;
    private ArrayList<IMessage> _pendingPublishQueue;

    private Messager(){
        _actionMap = new HashMap<Type, ActionQueue>();
        _pendingPublishQueue = new ArrayList<IMessage>();
    }

    public void Update(float elapsedTime){
        for(IMessage data : _pendingPublishQueue){
            _actionMap.get(data.getClass()).Fire(data);
        }

        _pendingPublishQueue.clear();
    }

    public void Publish(IMessage data){
        if(_actionMap.containsKey(data.getClass()))
            _pendingPublishQueue.add(data);
           // _actionMap.get(data.getClass()).Fire(data);
        else {
            Logger.log("message type " + data.getClass().getCanonicalName() + " not found!");
            for(Type key: _actionMap.keySet())
                Logger.log("type " + key.getClass().getCanonicalName() + " found");
        }
    }

    public void Subscribe(Type type, IAction action) {
        if (_actionMap.containsKey(type))
            _actionMap.get(type).AddToQueue(action);
        else{
            _actionMap.put(type, new ActionQueue());
            ActionQueue target = _actionMap.get(type);
            target.AddToQueue(action);
        }
    }
}
