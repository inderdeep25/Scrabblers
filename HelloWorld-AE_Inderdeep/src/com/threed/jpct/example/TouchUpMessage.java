package com.threed.jpct.example;

public class TouchUpMessage implements IMessage {
	float _pixelx, _pixely;

	public TouchUpMessage(float x, float y) {
		_pixelx = x;
		_pixely = y;
	}
	public static Class<TouchUpMessage> getClassType(){
		return TouchUpMessage.class;
	}
}
