package com.threed.jpct.example;

public class TouchDownMessage implements IMessage{
	public float _pixelx;
	public float _pixely;
	public TouchDownMessage(float x,float y){
		_pixelx=x;
		_pixely=y;
	}
	
	public static Class<TouchDownMessage> getClassType(){
		return TouchDownMessage.class;
	}
	

}
