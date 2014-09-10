package com.threed.jpct.example;

public class ArrayCoord {

	static float x;
	static float y;
	ArrayCoord(){
		
	}
	public static float ArrayCoordX(int touch_x ){
		x = touch_x/32;
		
		return x;
	}
	public static  float ArrayCoordY(int touch_y,int h,int w){
		y = (touch_y-(h-w)/2)/32;
		return y;
	}
}
