package com.threed.jpct.example;

import android.util.Log;

public class TouchDownMessage implements IMessage{
	public float _pixelx;
	public float _pixely;
	public TouchDownMessage(float x,float y){
		_pixelx=x;
		_pixely=y;
	//	Log.d("Coordinates","x="+Integer.toString(_pixelx)+"y="+Integer.toString(_pixely));
	}
	
	public static Class<TouchDownMessage> getClassType(){
		return TouchDownMessage.class;
	}
	

}
